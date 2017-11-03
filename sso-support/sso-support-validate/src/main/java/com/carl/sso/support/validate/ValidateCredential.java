/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

/**
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public interface ValidateCredential extends Credential {
    /**
     * 传入数据
     * @return
     */
    Object data();
}
