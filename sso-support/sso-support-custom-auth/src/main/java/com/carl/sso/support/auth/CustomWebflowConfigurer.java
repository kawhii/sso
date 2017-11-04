/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.auth;

import org.apereo.cas.web.flow.AbstractCasWebflowConfigurer;
import org.apereo.cas.web.flow.CasWebflowConstants;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.Flow;
import org.springframework.webflow.engine.ViewState;
import org.springframework.webflow.engine.builder.BinderConfiguration;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * 重新定义默认的web流程
 *
 * @author Carl
 * @date 2017/10/23
 * @since 1.6.0
 */
public class CustomWebflowConfigurer extends AbstractCasWebflowConfigurer {

    public CustomWebflowConfigurer(FlowBuilderServices flowBuilderServices, FlowDefinitionRegistry flowDefinitionRegistry) {
        super(flowBuilderServices, flowDefinitionRegistry);
    }

    @Override
    protected void doInitialize() throws Exception {
        final Flow flow = getLoginFlow();
        bindCredential(flow);
    }


    /**
     * 绑定输入信息
     *
     * @param flow
     */
    protected void bindCredential(Flow flow) {
        //重写绑定自定义credential
        createFlowVariable(flow, CasWebflowConstants.VAR_ID_CREDENTIAL, UsernamePasswordSysCredential.class);
        //登录页绑定新参数
        final ViewState state = (ViewState) flow.getState(CasWebflowConstants.STATE_ID_VIEW_LOGIN_FORM);
        final BinderConfiguration cfg = getViewStateBinderConfiguration(state);
        //由于用户名以及密码已经绑定，所以只需对新加系统参数绑定即可
        cfg.addBinding(new BinderConfiguration.Binding("system", null, false));
    }
}
