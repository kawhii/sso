/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.sso.rest.client.controller;

import com.carl.auth.sso.rest.client.config.ApplicationConfig;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Carl
 * @date 2017/9/14
 * @since JDK1.7
 */
@RunWith(SpringRunner.class)
@Import(value = ApplicationConfig.class)
@WebMvcTest(AuthUserController.class)
public class AuthUserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void login() throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String username = "rest-admin";
        String password = "123";
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String passwordMd5 = byteArrayToHex(md5.digest(password.getBytes("utf-8")));
        String code = base64en.encode((username + ":" + passwordMd5).getBytes());

        mvc.perform(MockMvcRequestBuilders.post("/login")
                .header("authorization", "Basic " + code)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    /**
     * 字节数组转16进制字符串
     * @param byteArray
     * @return
     */
    public static String byteArrayToHex(byte[] byteArray){
        char[] hexDigits = "0123456789abcdef".toCharArray();
        char[] resultCharArray =new char[byteArray.length * 2];
        int index = 0;

        for (byte b : byteArray) {

            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];

            resultCharArray[index++] = hexDigits[b& 0xf];

        }
        return new String(resultCharArray);
    }
}
