/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 认证中心监控平台，监控各服务运行情况
 * @author Carl
 */
@Configuration
@EnableAutoConfiguration
@EnableAdminServer
public class MonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }
}
