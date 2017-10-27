/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 验证码控制器
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
@RequestMapping("/")
public class CaptchaController implements Controller {
    private ICaptchaWriter<String> captchaWriter;
    private SessionCaptchaResultAware<String> aware;

    public CaptchaController(ICaptchaWriter<String> captchaWriter, SessionCaptchaResultAware<String> aware) {
        this.captchaWriter = captchaWriter;
        this.aware = aware;
    }

    public SessionCaptchaResultAware<String> getAware() {
        return aware;
    }

    public ICaptchaWriter<String> getCaptchaWriter() {
        return captchaWriter;
    }


    @RequestMapping(value = CaptachaConstants.REQUEST_MAPPING)
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream outputStream =  response.getOutputStream();
        //存储验证码到session
        String text = getAware().getAndStore(request.getSession());
        getCaptchaWriter().write(text, outputStream);
        outputStream.flush();
        return null;
    }
}
