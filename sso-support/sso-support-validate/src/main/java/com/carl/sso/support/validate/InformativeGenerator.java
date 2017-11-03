/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

/**
 * @param <T> 发送信息
 * @param <I> 构建信息
 * @author Carl
 * @date 2017/11/2
 * @since
 */
public interface InformativeGenerator<T extends Informative, I extends Credential> {

    /**
     * 生成发送信息
     * @param i
     * @return
     */
    T generate(I i);
}
