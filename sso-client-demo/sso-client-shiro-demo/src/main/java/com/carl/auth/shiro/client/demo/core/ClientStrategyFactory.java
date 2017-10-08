/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.core;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户端存储器
 *
 * @author Carl
 * @date 2017/10/8
 * @since 1.5.0
 */
public class ClientStrategyFactory {
    private Map<String , ClientStrategy> clientStrategy = new HashMap<>();

    public ClientStrategyFactory(Map<String, ClientStrategy> clientStrategy) {
        this.clientStrategy = clientStrategy;
    }

    public Map<String, ClientStrategy> getClientStrategy() {
        return clientStrategy;
    }
}
