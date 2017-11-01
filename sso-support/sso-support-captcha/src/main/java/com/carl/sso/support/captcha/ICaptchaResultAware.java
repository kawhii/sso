/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */



package com.carl.sso.support.captcha;

/**
 * 验证码输出结果对象
 *
 * @author Carl
 * @date 2017/10/27
 * @since
 */
public interface ICaptchaResultAware<S, T> {
    /**
     * 获取数据结果
     *
     * @param s 存储器
     * @return
     */
    T getAndStore(S s);
}
