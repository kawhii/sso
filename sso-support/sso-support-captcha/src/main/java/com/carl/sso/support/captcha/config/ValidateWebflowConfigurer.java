/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha.config;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.AbstractCasWebflowConfigurer;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.springframework.context.ApplicationContext;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.ActionState;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * @date 2017/10/30
 */
public class ValidateWebflowConfigurer extends AbstractCasWebflowConfigurer {
    /**
     * 校验码动作
     */
    public static final String VALIDATE_CAPTCHA_ACTION = "validateCaptchaAction";

    public ValidateWebflowConfigurer(FlowBuilderServices flowBuilderServices, FlowDefinitionRegistry loginFlowDefinitionRegistry, ApplicationContext applicationContext, CasConfigurationProperties casProperties) {
        super(flowBuilderServices, loginFlowDefinitionRegistry);
    }

    @Override
    protected void doInitialize() throws Exception {
        createPasswordResetValidateFlow();
        createLoginValidateValidateFlow();
    }

    /**
     * 登录校验流程
     */
    private void createLoginValidateValidateFlow() {
        final Flow flow = getLoginFlow();
        if (flow != null) {
            final ActionState state = (ActionState) flow.getState(CasWebflowConstants.TRANSITION_ID_REAL_SUBMIT);
            final List<Action> currentActions = new ArrayList<>();
            state.getActionList().forEach(currentActions::add);
            currentActions.forEach(a -> state.getActionList().remove(a));

            state.getActionList().add(createEvaluateAction("validateLoginCaptchaAction"));
            currentActions.forEach(a -> state.getActionList().add(a));

            state.getTransitionSet().add(createTransition("captchaError", CasWebflowConstants.STATE_ID_INIT_LOGIN_FORM));
        }
    }

    /**
     * 发送邮箱前输入验证码流程
     */
    private void createPasswordResetValidateFlow() {
        final Flow flow = getLoginFlow();
        if (flow != null) {
            ViewState accountInfo = (ViewState) flow.getState(CasWebflowConstants.VIEW_ID_SEND_RESET_PASSWORD_ACCT_INFO);
            //提交查找用户后，先校验验证码
            createTransitionForState(accountInfo, "findAccount", VALIDATE_CAPTCHA_ACTION, true);
            //校验图片动作
            ActionState actionState = createActionState(flow, VALIDATE_CAPTCHA_ACTION, createEvaluateAction(VALIDATE_CAPTCHA_ACTION));
            //失败重新是发送页
            createTransitionForState(actionState, CasWebflowConstants.TRANSITION_ID_RESET_PASSWORD,
                    CasWebflowConstants.VIEW_ID_SEND_RESET_PASSWORD_ACCT_INFO);
            //发送邮件
            createTransitionForState(actionState, "sendInstructions", "sendInstructions");
        }
    }
}