/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.auth.config;

import com.carl.sso.support.auth.CustomWebflowConfigurer;
import org.apereo.cas.config.CasWebflowContextConfiguration;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.web.flow.CasWebflowConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;

/**
 * @author Carl
 * @date 2017/10/23
 * @since 1.6.0
 */
@Configuration("customerAuthWebflowConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
@AutoConfigureBefore(value = CasWebflowContextConfiguration.class)
public class CustomerAuthWebflowConfiguration {
    @Autowired
    @Qualifier("logoutFlowRegistry")
    private FlowDefinitionRegistry logoutFlowRegistry;
    @Autowired
    @Qualifier("loginFlowRegistry")
    private FlowDefinitionRegistry loginFlowRegistry;

    @Autowired
    @Qualifier("builder")
    private FlowBuilderServices builder;

    @Bean
    public CasWebflowConfigurer customWebflowConfigurer() {
        final CustomWebflowConfigurer c = new CustomWebflowConfigurer(builder, loginFlowRegistry);
        c.setLogoutFlowDefinitionRegistry(logoutFlowRegistry);
        return c;
    }
}
