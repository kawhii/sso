/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 绑定页面控制器
 *
 * @author Carl
 * @date 2017/10/9
 * @since
 */
@Controller
public class BindController {

    /**
     * 转发绑定页面
     * @param client
     * @return
     */
    @RequestMapping("/bind/{client}")
    public String bindPage(@PathVariable("client") String client) {
        return "bind/" + client;
    }

    @RequestMapping("/bind/user")
    public String bindPage() {
        //绑定用户动作
        return null;
    }
}
