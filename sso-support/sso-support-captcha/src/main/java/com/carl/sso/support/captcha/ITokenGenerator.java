/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */



package com.carl.sso.support.captcha;

/**
 * 校验码生成器
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public interface ITokenGenerator<T> {
    /**
     * 生成校验码
     *
     * @return
     */
    T generator();
}
