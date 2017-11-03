/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

import com.carl.sso.support.validate.exception.ValidateSenderException;

/**
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public interface IValidateService<T extends Credential, O extends ValidateCredential> {
    /**
     * 发送数据
     *
     * @param t
     */
    void send(T t) throws ValidateSenderException;

    /**
     * 校验
     *
     * @param o
     * @return
     */
    ValidateResult validate(O o);
}
