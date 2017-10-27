/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha;

import com.carl.sso.support.captcha.imp.cage.CageCaptchaController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
@SpringBootApplication
public class ApplicationTest {
    @Bean
    protected Controller test() {
        return new CageCaptchaController();
    }
    public static void main(String[] args) {
        SpringApplication.run(ApplicationTest.class, args);
    }

}
