/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha.config;

import com.carl.sso.support.captcha.SessionCaptchaResultProvider;
import com.carl.sso.support.captcha.imp.cage.CageCaptchaController;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Carl
 * @date 2017/10/28
 * @since 1.5.0
 */
@Configuration("captchaConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class CaptchaConfiguration {

    //注册bean到spring容器
    @Bean
    @ConditionalOnMissingBean(name = "captchaController")
    public CageCaptchaController captchaController() {
        return new CageCaptchaController(captchaResultProvider());
    }

    @Bean
    public SessionCaptchaResultProvider captchaResultProvider() {
        return new SessionCaptchaResultProvider();
    }
}
