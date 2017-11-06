/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha.config;

import com.carl.sso.support.captcha.SessionCaptchaResultProvider;
import com.carl.sso.support.captcha.action.CaptchaAwareFactory;
import com.carl.sso.support.captcha.action.ValidateCaptchaAction;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.pm.PasswordManagementService;
import org.apereo.cas.pm.config.PasswordManagementConfiguration;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.execution.Action;

/**
 * @author Carl
 * @date 2017/10/30
 * @since
 */
@Configuration("validateWebflowConfiguation")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@AutoConfigureAfter(PasswordManagementConfiguration.class)
public class ValidateWebflowConfiguation {
    @Autowired
    private CasConfigurationProperties casProperties;

    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowDefinitionRegistry;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FlowBuilderServices flowBuilderServices;

    @Autowired
    private SessionCaptchaResultProvider captchaResultProvider;

    @Autowired
    private PasswordManagementService passwordManagementService;

    @ConditionalOnMissingBean(name = "validateWebflowConfigurer")
    @RefreshScope
    @Bean
    public CasWebflowConfigurer validateWebflowConfigurer() {
        ValidateWebflowConfigurer validateWebflowConfigurer = new ValidateWebflowConfigurer(flowBuilderServices, loginFlowDefinitionRegistry, applicationContext, casProperties);
        return validateWebflowConfigurer;
    }

    @ConditionalOnMissingBean(name = "validateCaptchaAction")
    @Bean
    @RefreshScope
    public Action validateCaptchaAction() {
        ValidateCaptchaAction validateCaptchaAction = new ValidateCaptchaAction(captchaResultProvider, captchaAwareFactory(), passwordManagementService);
        return validateCaptchaAction;
    }

    @Bean
    public CaptchaAwareFactory captchaAwareFactory() {
        return new CaptchaAwareFactory();
    }
}