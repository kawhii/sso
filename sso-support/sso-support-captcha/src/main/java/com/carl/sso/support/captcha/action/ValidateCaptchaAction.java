/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha.action;

import com.carl.sso.support.captcha.ICaptchaResultProvider;
import org.apereo.cas.pm.PasswordManagementService;
import org.apereo.cas.web.support.WebUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 校验动作
 *
 * @author Carl
 * @date 2017/10/30
 * @since
 */
public class ValidateCaptchaAction extends AbstractAction {
    //验证码存储器
    private ICaptchaResultProvider<HttpSession, String> captchaResultProvider;
    //流程工厂器
    private CaptchaAwareFactory awareFactory;

    private PasswordManagementService passwordManagementService;


    /**
     * 验证码结果
     */
    public static final String CAPTCHA_RESULT = "validateCaptchaResult";

    /**
     * 邮件校验
     */
    public static final String EMAIL_RESULT = "validateEmailResult";

    /**
     * 用户名参数
     */
    public static final String USERNAME_PARAM = "username";
    /**
     * 前端验证码
     */
    public static final String CODE_PARAM = "validateCode";

    public ValidateCaptchaAction(ICaptchaResultProvider<HttpSession, String> captchaResultProvider, CaptchaAwareFactory awareFactory, PasswordManagementService passwordManagementService) {
        this.captchaResultProvider = captchaResultProvider;
        this.awareFactory = awareFactory;
        this.passwordManagementService = passwordManagementService;
    }

    @Override
    protected Event doExecute(RequestContext context) throws Exception {
        String event = context.getCurrentTransition().getId();
        ICaptchaAware aware = this.awareFactory.get(event);
        HttpServletRequest request = WebUtils.getHttpServletRequest();
        HttpSession httpSession = request.getSession();
        //校验码
        String inCode = request.getParameter(CODE_PARAM);
        //邮箱
        String username = request.getParameter(USERNAME_PARAM);

        if (this.captchaResultProvider.validate(httpSession, inCode)) {
            try {
                passwordManagementService.findEmail(username);
            } catch (Exception e) {
                e.printStackTrace();
                context.getFlowScope().put(EMAIL_RESULT, "邮箱错误");
                context.getFlowScope().remove(CAPTCHA_RESULT);
                return aware != null ? new Event(this, aware.fail()) : error();
            }
            //成功
            return aware != null ? new Event(this, aware.success()) : success();
        } else {
            context.getFlowScope().put(CAPTCHA_RESULT, "验证码错误");
            context.getFlowScope().put(USERNAME_PARAM, username);
            return aware != null ? new Event(this, aware.fail()) : error();
        }
    }
}
