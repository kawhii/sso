/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.auth.config;

import com.carl.sso.support.auth.handler.UsernamePasswordSystemAuthenticationHandler;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carl
 * @date 2017/10/23
 * @since 1.6.0
 */
@Configuration("customAuthenticationEventExecutionPlanConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CustomAuthenticationEventExecutionPlanConfiguration implements AuthenticationEventExecutionPlanConfigurer {
    @Autowired
    @Qualifier("servicesManager")
    private ServicesManager servicesManager;

    @Autowired
    @Qualifier("jdbcPrincipalFactory")
    public PrincipalFactory jdbcPrincipalFactory;


    /**
     * 注册验证器
     *
     * @return
     */
    @Bean
    public AuthenticationHandler customAuthenticationHandler() {
        //优先验证
        return new UsernamePasswordSystemAuthenticationHandler("customAuthenticationHandler",
                servicesManager, jdbcPrincipalFactory, 1);
    }

    //注册自定义认证器
    @Override
    public void configureAuthenticationExecutionPlan(final AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(customAuthenticationHandler());
    }
}
