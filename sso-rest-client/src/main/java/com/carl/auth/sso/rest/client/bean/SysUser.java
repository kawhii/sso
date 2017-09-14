/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.sso.rest.client.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl
 * @date 2017/9/14
 * @since JDK1.7
 */
public class SysUser {
    @JsonProperty("id")
    @NotNull
    private String username;
    @JsonProperty("@class")
    //需要返回实现org.apereo.cas.authentication.principal.Principal的类名接口
    private String clazz = "org.apereo.cas.authentication.principal.SimplePrincipal";
    @JsonProperty("attributes")
    private Map<String, Object> attributes = new HashMap<>();

    @JsonIgnore
    @NotNull
    private String password;

    @JsonIgnore
    //用户是否不可用
    private boolean disable = false;
    @JsonIgnore
    //用户是否过期
    private boolean expired = false;

    @JsonIgnore
    //是否锁定
    private boolean locked = false;

    public boolean isLocked() {
        return locked;
    }

    public SysUser setLocked(boolean locked) {
        this.locked = locked;
        return this;
    }

    public boolean isDisable() {
        return disable;
    }

    public SysUser setDisable(boolean disable) {
        this.disable = disable;
        return this;
    }

    public boolean isExpired() {
        return expired;
    }

    public SysUser setExpired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SysUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public SysUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getClazz() {
        return clazz;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public SysUser setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    @JsonIgnore
    public SysUser addAttribute(String key, Object val) {
        getAttributes().put(key, val);
        return this;
    }
}
