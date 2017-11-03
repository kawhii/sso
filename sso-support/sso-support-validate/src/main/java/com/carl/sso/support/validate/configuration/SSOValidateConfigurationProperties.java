/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author Carl
 * @date 2017/11/2
 * @since
 */
@ConfigurationProperties(value = "sso.validate")
public class SSOValidateConfigurationProperties implements Serializable {
    public static final String PREFIX = "sso.validate";

    @NestedConfigurationProperty
    private MailProperties mail = new MailProperties();

    public MailProperties getMail() {
        return mail;
    }

    public void setMail(MailProperties mail) {
        this.mail = mail;
    }
}
