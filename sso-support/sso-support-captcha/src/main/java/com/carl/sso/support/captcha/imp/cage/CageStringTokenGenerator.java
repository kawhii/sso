/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */


package com.carl.sso.support.captcha.imp.cage;

import com.github.cage.token.RandomTokenGenerator;
import com.carl.sso.support.captcha.string.StringTokenGenerator;

/**
 * cage字符串生成器
 *
 * @author Carl
 * @date 2017/10/27
 * @since 2.3.8
 */
public class CageStringTokenGenerator extends StringTokenGenerator {
    private RandomTokenGenerator generator = new RandomTokenGenerator(null, 4, 0);

    @Override
    public String generator() {
        return generator.next();
    }
}
