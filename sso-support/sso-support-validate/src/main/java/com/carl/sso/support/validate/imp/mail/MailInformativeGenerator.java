/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate.imp.mail;

import com.carl.sso.support.validate.InformativeGenerator;
import com.carl.sso.support.validate.configuration.MailProperties;

/**
 * @author Carl
 * @date 2017/11/2
 * @since
 */
public class MailInformativeGenerator implements InformativeGenerator<MailInformative, MailCredential> {
    private MailProperties properties;

    public MailInformativeGenerator(MailProperties properties) {
        this.properties = properties;
    }

    @Override
    public MailInformative generate(MailCredential mailCredential) {
        //生成随机码
        int code = (int) ((Math.random() * 9 + 1) * Math.pow(10, properties.getCodeLen()));
        String strCode = String.valueOf(code);
        MailInformative informative = new MailInformative()
                .setCode(strCode)
                .setEffective(properties.getEffective())
                .setContent(String.format(properties.getContent(), strCode))
                .setFromMail(properties.getFrom())
                .setToMail(mailCredential.getMail())
                .setId(mailCredential.id())
                .setSubject(properties.getSubject());

        return informative;
    }
}
