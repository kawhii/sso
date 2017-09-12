/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.sso.server;

import org.apache.shiro.crypto.hash.ConfigurableHashService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Carl
 * @date 2017/9/12
 * @since JDK1.7
 */
public class PasswordSaltTest {
    private String staticSalt = ".";
    private String algorithmName = "MD5";
    private String encodedPassword = "123";
    private String dynaSalt = "admin_en";
    @Test
    public void test() throws Exception {
        ConfigurableHashService hashService = new DefaultHashService();
        hashService.setPrivateSalt(ByteSource.Util.bytes(this.staticSalt));
        hashService.setHashAlgorithmName(this.algorithmName);
        hashService.setHashIterations(2);
        HashRequest request = new HashRequest.Builder()
                .setSalt(dynaSalt)
                .setSource(encodedPassword)
                .build();
        String res =  hashService.computeHash(request).toHex();
//        System.out.println(res);
        Assert.assertEquals("bfb194d5bd84a5fc77c1d303aefd36c3", res);
    }
}
