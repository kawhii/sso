/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.confg.pros;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carl
 * @date 2017/10/8
 * @since
 */
@Component
@ConfigurationProperties(prefix="github") //接收application.yml中的github下面的属性
public class GithubProperties {

    private List<String> bindId = new ArrayList<>();

    public List<String> getBindId() {
        return bindId;
    }

    public GithubProperties setBindId(List<String> bindId) {
        this.bindId = bindId;
        return this;
    }
}
