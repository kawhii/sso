/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate.config;

import com.carl.sso.support.validate.configuration.SSOValidateConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carl
 * @date 2017/11/2
 * @since
 */
@Configuration("ssoValidateConfiguration")
@EnableConfigurationProperties(SSOValidateConfigurationProperties.class)
public class SSOValidateConfiguration {
    @Autowired
    private SSOValidateConfigurationProperties properties;
}
