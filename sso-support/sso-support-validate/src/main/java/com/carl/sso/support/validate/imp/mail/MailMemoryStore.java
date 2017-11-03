/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate.imp.mail;

import com.carl.sso.support.validate.IStore;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public class MailMemoryStore implements IStore<MailInformative, MailCredential> {
    private Map<String, MailInformative> store = new HashMap<>();

    @Override
    public void save(MailInformative mailInformative) {
        store.put(mailInformative.id(), mailInformative);
    }

    @Override
    public MailInformative get(MailCredential mailCredential) {
        return store.get(mailCredential.id());
    }

    @Override
    public void remove(MailCredential mailCredential) {
        store.remove(mailCredential.id());
    }
}
