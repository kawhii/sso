/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

/**
 * 验证数据
 *
 * @author Carl
 * @date 2017/11/2
 * @since
 */
public interface IValidator<T extends ValidateCredential> {
    /**
     * 验证器名称
     *
     * @return
     */
    String name();


    /**
     * 鉴定验证码
     *
     * @param t
     * @return
     */
    ValidateResult identify(T t);
}
