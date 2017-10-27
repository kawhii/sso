/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha;


/**
 * 验证码结果提供者
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public interface ICaptchaResultProvider<T, S>{
    /**
     * 把S存储到T
     *
     * @param t
     * @param s
     */
    void store(T t, S s);

    /**
     * 在T中提供出s
     * @param t
     * @return
     */
    S get(T t);
}

