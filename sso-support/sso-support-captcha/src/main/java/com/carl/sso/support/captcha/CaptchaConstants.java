/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha;

/**
 * 验证码常亮
 *
 * @author Carl
 * @date 2017/10/27
 * @since
 */
public interface CaptchaConstants {
    /**
     * 验证码存储常量，可以存储session等等
     */
    String STORE_CODE = "captcha_code";
    /**
     * 请求路径
     */
    String REQUEST_MAPPING = "/captcha";
}