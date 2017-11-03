/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

import com.carl.sso.support.validate.exception.ValidateSenderException;

/**
 * 校验发送者，发送短信，邮件等
 *
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public interface ISender<T extends Informative> {

    /**
     * @param t
     * @throws ValidateSenderException 发送失败时抛出异常
     */
    void send(T t) throws ValidateSenderException;
}
