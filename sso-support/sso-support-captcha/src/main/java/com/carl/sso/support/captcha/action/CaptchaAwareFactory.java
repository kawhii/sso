/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha.action;
        import javax.annotation.PostConstruct;
        import java.util.HashMap;
        import java.util.Map;

/**
 * @author Carl
 * @date 2017/10/30
 * @since
 */
public class CaptchaAwareFactory {
    private Map<String, ICaptchaAware> awareRelation = new HashMap<>();


    public void register(ICaptchaAware aware) {
        awareRelation.put(aware.id(), aware);
    }

    public ICaptchaAware get(String id) {
        return awareRelation.get(id);
    }

    @PostConstruct
    private void afterInit() {
        //注册当在充值密码发送邮件，当校验码成功，转发到sendInstructions，否则resetPassword findAccount
        register(DefaultCaptchaAware.newInstance("findAccount", "sendInstructions", "resetPassword"));
    }
}