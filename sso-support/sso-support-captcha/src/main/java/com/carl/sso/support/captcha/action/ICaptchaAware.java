/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.captcha.action;


/**
 * @author Carl
 * @date 2017/10/30
 * @since
 */
public interface ICaptchaAware {
    /**
     * 识别的id流转
     * @return
     */
    String id();

    /**
     * 成功流转
     * @return
     */
    String success();

    /**
     * 失败流转
     * @return
     */
    String fail();
}
