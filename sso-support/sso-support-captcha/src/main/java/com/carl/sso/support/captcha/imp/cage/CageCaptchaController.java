/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha.imp.cage;

import com.carl.sso.support.captcha.CaptchaController;
import com.carl.sso.support.captcha.ICaptchaWriter;
import com.carl.sso.support.captcha.SessionCaptchaResultAware;
import com.carl.sso.support.captcha.SessionCaptchaResultProvider;
import com.carl.sso.support.captcha.string.StringCaptchaResultAware;

/**
 * Cage验证码控制器
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public class CageCaptchaController extends CaptchaController {

    public CageCaptchaController(ICaptchaWriter<String> captchaWriter, SessionCaptchaResultAware<String> aware) {
        super(captchaWriter, aware);
    }

    public CageCaptchaController() {
        super(new CageStringCaptchaWriter(), new StringCaptchaResultAware(new SessionCaptchaResultProvider(), new CageStringTokenGenerator()));
    }

    public CageCaptchaController(SessionCaptchaResultProvider provider) {
        super(new CageStringCaptchaWriter(), new StringCaptchaResultAware(provider, new CageStringTokenGenerator()));
    }
}
