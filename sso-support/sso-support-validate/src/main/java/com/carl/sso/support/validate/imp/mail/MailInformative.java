/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate.imp.mail;

import com.carl.sso.support.validate.Informative;

import java.util.Date;

/**
 * @author Carl
 * @date 2017/11/2
 * @since
 */
public class MailInformative implements Informative {
    //发送邮件的from
    private String fromMail;
    //目标邮箱
    private String toMail;
    //邮件标题
    private String subject;
    //邮件内容
    private String content;
    //邮件编码
    private String code;
    //有效时长，秒
    private long effective;
    //信息唯一标志
    private String id;


    public String getFromMail() {
        return fromMail;
    }

    public MailInformative setFromMail(String fromMail) {
        this.fromMail = fromMail;
        return this;
    }

    public String getToMail() {
        return toMail;
    }

    public MailInformative setToMail(String toMail) {
        this.toMail = toMail;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public MailInformative setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MailInformative setContent(String content) {
        this.content = content;
        return this;
    }

    public String getCode() {
        return code;
    }

    public MailInformative setCode(String code) {
        this.code = code;
        return this;
    }

    public long getEffective() {
        return effective;
    }

    public MailInformative setEffective(long effective) {
        this.effective = effective;
        return this;
    }

    public String getId() {
        return id;
    }

    public MailInformative setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public long effective() {
        return getEffective();
    }

    @Override
    public long time() {
        return new Date().getTime();
    }

    @Override
    public String id() {
        return getId();
    }
}
