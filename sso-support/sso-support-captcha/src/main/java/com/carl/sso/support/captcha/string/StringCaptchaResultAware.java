/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha.string;

import com.carl.sso.support.captcha.ICaptchaResultProvider;
import com.carl.sso.support.captcha.ITokenGenerator;
import com.carl.sso.support.captcha.SessionCaptchaResultAware;

/**
 * 字符串验证码识别器
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public class StringCaptchaResultAware extends SessionCaptchaResultAware {
    public StringCaptchaResultAware(ICaptchaResultProvider provider, ITokenGenerator generator) {
        super(provider, generator);
    }
}
