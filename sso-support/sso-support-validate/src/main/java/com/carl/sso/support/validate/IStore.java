/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

/**
 * 验证存储器
 *
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public interface IStore<T extends Informative, I extends Credential> {
    /**
     * 保存验证数据
     */
    void save(T t);

    /**
     * 获取验证数据
     *
     * @param i
     * @return
     */
    T get(I i);

    /**
     *
     * 移除
     */
    void remove(I i);
}
