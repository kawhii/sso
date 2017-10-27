/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码输出者
 *
 * @author Carl
 * @date 2017/10/27
 * @since
 */
public interface ICaptchaWriter<T> {

    /**
     * 对外写出验证码并且返回结果集
     *
     * @param outputStream
     * @return
     * @throws IOException
     */
    void write(T t, OutputStream outputStream) throws IOException;
}
