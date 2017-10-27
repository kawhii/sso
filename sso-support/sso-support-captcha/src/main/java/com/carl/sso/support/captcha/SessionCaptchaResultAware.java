/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha;

import javax.servlet.http.HttpSession;

/**
 * 会话意识器存储验证码
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public abstract class SessionCaptchaResultAware<T> implements ICaptchaResultAware<HttpSession, T>{
    private ICaptchaResultProvider<HttpSession, T> provider;
    private ITokenGenerator<T> generator;

    public SessionCaptchaResultAware(ICaptchaResultProvider<HttpSession, T> provider, ITokenGenerator<T> generator) {
        this.provider = provider;
        this.generator = generator;
    }

    public ITokenGenerator<T> getGenerator() {
        return generator;
    }

    public ICaptchaResultProvider<HttpSession, T> getProvider() {
        return provider;
    }

    @Override
    public T getAndStore(HttpSession httpSession) {
        T t = getGenerator().generator();
        getProvider().store(httpSession, t);
        return t;
    }
}
