/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha.imp.cage;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.carl.sso.support.captcha.string.StringCaptchaWriter;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

/**
 * http://akiraly.github.io/cage/quickstart.html 验证码库
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public class CageStringCaptchaWriter extends StringCaptchaWriter {
    private Cage cage = new GCage();

    @Override
    public void write(String text, OutputStream outputStream) throws IOException {
        ImageIO.write(cage.drawImage(text),"png", outputStream);
    }
}
