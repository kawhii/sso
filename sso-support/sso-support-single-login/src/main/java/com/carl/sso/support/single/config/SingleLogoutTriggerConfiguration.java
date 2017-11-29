/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.single.config;

import com.carl.sso.support.single.listener.TGTCreateEventListener;
import com.carl.sso.support.single.service.TriggerLogoutService;
import com.carl.sso.support.single.service.UserIdObtainServiceImpl;
import org.apereo.cas.CentralAuthenticationService;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 登出配置
 *
 * @author Carl
 * @date 2017/11/29
 */
@Configuration("singleLogoutTriggerConfiguration")
@EnableConfigurationProperties(CasConfigurationProperties.class)
public class SingleLogoutTriggerConfiguration {
    @Autowired
    private CentralAuthenticationService centralAuthenticationService;

    /**
     * 触发登出服务
     *
     * @return 触发登出服务
     */
    @Bean
    protected TriggerLogoutService triggerLogoutService() {
        return new TriggerLogoutService(centralAuthenticationService);
    }

    @Bean
    //注册事件监听tgt的创建
    protected TGTCreateEventListener tgtCreateEventListener() {
        TGTCreateEventListener listener = new TGTCreateEventListener(triggerLogoutService(), new UserIdObtainServiceImpl());
        return listener;
    }
}
