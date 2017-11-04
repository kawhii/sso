/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.auth;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apereo.cas.authentication.RememberMeUsernamePasswordCredential;

import javax.validation.constraints.Size;

/**
 * 用户名，密码，系统
 *
 * @author Carl
 * @date 2017/10/23
 * @since
 */
public class UsernamePasswordSysCredential extends RememberMeUsernamePasswordCredential {
    @Size(min = 2, message = "require system")
    private String system;

    public String getSystem() {
        return system;
    }

    public UsernamePasswordSysCredential setSystem(String system) {
        this.system = system;
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(this.system)
                .toHashCode();
    }
}
