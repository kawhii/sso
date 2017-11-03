/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate;

import java.io.Serializable;

/**
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public interface Informative extends Credential, Serializable {
    /**
     * 有效时间秒, -1 无限制
     *
     * @return
     */
    long effective();

    /**
     * 创建时间
     *
     * @return
     */
    long time();
}
