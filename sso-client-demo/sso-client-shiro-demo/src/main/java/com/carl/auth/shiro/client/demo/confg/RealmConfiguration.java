/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.confg;

import io.buji.pac4j.realm.Pac4jRealm;
import org.apache.shiro.realm.Realm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carl
 * @date 2017/10/8
 * @since 1.5.0
 */
@Configuration
public class RealmConfiguration {
    @Bean
    public Realm pac4jRealm() {
        return new Pac4jRealm();
    }
}
