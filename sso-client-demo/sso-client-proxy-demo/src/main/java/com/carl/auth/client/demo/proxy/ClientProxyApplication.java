/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.client.demo.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 中转客户端
 *
 * @author Carl
 * @date 2017/10/21
 * @since
 */
@SpringBootApplication
public class ClientProxyApplication {
    // 启动的时候要注意，由于我们在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
    @Autowired
    private RestTemplateBuilder builder;

    // 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientProxyApplication.class, args);
    }
}
