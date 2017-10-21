/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.client.demo.proxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;


/**
 * @author Carl
 * @date 2017/10/21
 * @since
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    /**
     * qq代理转发
     * @return
     */
    @RequestMapping(value = "/qq", produces = {"application/json"})
    public Object qq(@RequestParam("access_token") String access_token, HttpServletResponse response) {
        ResponseEntity<String> resp =  restTemplate.exchange("https://graph.qq.com/oauth2.0/me?access_token=" + access_token, HttpMethod.GET, null, String.class);
        String res = resp.getBody();
        //todo 这里应该判断拿不到open应该为失败，设置错误状态码

        response.setContentType("application/json");
        return res.replace("callback( ","").replace(" );", "").replace("\n", "");
    }
}
