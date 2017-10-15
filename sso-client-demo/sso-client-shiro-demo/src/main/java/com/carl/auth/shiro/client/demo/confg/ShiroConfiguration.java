/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.confg;


import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * 对shiro的安全配置，是对cas的登录策略进行配置
 *
 * @author Carl
 * @date 2017/9/16
 * @since 1.0.0
 */
@Configuration
public class ShiroConfiguration extends AbstractShiroWebFilterConfiguration {
    @Value("#{ @environment['cas.prefixUrl'] ?: null }")
    private String prefixUrl;
    @Value("#{ @environment['cas.loginUrl'] ?: null }")
    private String casLoginUrl;
    @Value("#{ @environment['cas.callbackUrl'] ?: null }")
    private String callbackUrl;
    @Value("#{ @environment['cas.serviceUrl'] ?: null }")
    private String serviceUrl;


    /**
     * cas核心过滤器，把支持的client写上，filter过滤时才会处理，clients必须在casConfig.clients已经注册
     *
     * @return
     */
    @Bean
    public Filter casSecurityFilter() {
        SecurityFilter filter = new SecurityFilter();
        filter.setClients("cas,rest");
        filter.setConfig(casConfig());
        return filter;
    }



    /**
     * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
     * @return
     */
    @Bean
    protected CasRestFormClient casRestFormClient() {
        CasRestFormClient casRestFormClient = new CasRestFormClient();
        casRestFormClient.setConfiguration(casConfiguration());
        casRestFormClient.setName("rest");
        return casRestFormClient;
    }


    @Bean
    protected Clients clients() {
        //可以设置默认client
        Clients clients = new Clients();
        //支持的client全部设置进去
        clients.setClients(casClient(), casRestFormClient());
        return clients;
    }


    @Bean
    protected Config casConfig() {
        Config config = new Config();
        config.setClients(clients());
        return config;
    }

    /**
     * cas的基本设置，包括或url等等，rest调用协议等
     * @return
     */
    @Bean
    public CasConfiguration casConfiguration() {
        CasConfiguration casConfiguration = new CasConfiguration(casLoginUrl);
        casConfiguration.setProtocol(CasProtocol.CAS30);
        casConfiguration.setPrefixUrl(prefixUrl);
        return casConfiguration;
    }

    @Bean
    public CasClient casClient() {
        CasClient casClient = new CasClient();
        casClient.setConfiguration(casConfiguration());
        casClient.setCallbackUrl(callbackUrl);
        casClient.setName("cas");
        return casClient;
    }


    /**
     * 路径过滤设置
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/callback", "callbackFilter");
        definition.addPathDefinition("/logout", "logoutFilter");
        definition.addPathDefinition("/**", "casSecurityFilter");
        return definition;
    }


    /**
     * 对过滤器进行调整
     *
     * @return
     */
    @Bean
    protected ShiroFilterFactoryBean shiroFilterFactoryBean() {
        //把subject对象设为subjectFactory
        //由于cas代理了用户，所以必须通过cas进行创建对象
        ((DefaultSecurityManager) securityManager).setSubjectFactory(new Pac4jSubjectFactory());

        ShiroFilterFactoryBean filterFactoryBean = super.shiroFilterFactoryBean();
        filterFactoryBean.setFilters(shiroFilters());
        return filterFactoryBean;
    }


    /**
     * 对shiro的过滤策略进行明确
     * @return
     */
    @Bean
    protected Map<String, Filter> shiroFilters() {
        //过滤器设置
        Map<String, Filter> filters = new HashMap<>();
        filters.put("casSecurityFilter", casSecurityFilter());
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(casConfig());
        filters.put("callbackFilter", callbackFilter);

        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setConfig(casConfig());
        logoutFilter.setCentralLogout(true);
        logoutFilter.setDefaultUrl(serviceUrl);
        filters.put("logoutFilter", logoutFilter);
        return filters;
    }
}
