/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Carl
 * @date 2017/10/8
 * @since 1.5.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class ShiroClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroClientApplication.class, args);
    }
}
