/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha;

import javax.servlet.http.HttpSession;

/**
 * session提供
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public class SessionCaptchaResultProvider implements ICaptchaResultProvider<HttpSession, String> {
    @Override
    public void store(HttpSession httpSession, String s) {
        httpSession.setAttribute(CaptachaConstants.STORE_CODE, s);
    }

    @Override
    public String get(HttpSession httpSession) {
        return (String)httpSession.getAttribute(CaptachaConstants.STORE_CODE);
    }
}
