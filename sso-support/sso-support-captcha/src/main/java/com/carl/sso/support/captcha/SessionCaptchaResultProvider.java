/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * session提供
 *
 * @author Carl
 * @date 2017/10/27
 * @since
 */
public class SessionCaptchaResultProvider implements ICaptchaResultProvider<HttpSession, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionCaptchaResultProvider.class);
    @Override
    public void store(HttpSession httpSession, String s) {
        httpSession.setAttribute(CaptchaConstants.STORE_CODE, s);
    }

    @Override
    public String get(HttpSession httpSession) {
        return (String) httpSession.getAttribute(CaptchaConstants.STORE_CODE);
    }

    @Override
    public boolean validate(HttpSession store, String code) {
        String relCode = get(store);
        if (!StringUtils.isEmpty(relCode) && relCode.equals(code)) {
            //校验完清空
            store(store, null);
            return true;
        }
        return false;
    }
}
