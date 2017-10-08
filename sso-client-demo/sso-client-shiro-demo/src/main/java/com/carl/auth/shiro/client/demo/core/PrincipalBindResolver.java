/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.core;

/**
 *
 * 判断用户是否已经绑定
 *
 * @author Carl
 * @date 2017/10/8
 * @since 1.5.0
 */
public interface PrincipalBindResolver {
    /**
     * 判断用户是否已经绑定
     *
     * @param id
     * @return
     */
    boolean isBind(String id);
}
