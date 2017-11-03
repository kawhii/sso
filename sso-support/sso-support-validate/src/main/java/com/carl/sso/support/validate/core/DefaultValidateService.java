/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.sso.support.validate.core;

import com.carl.sso.support.validate.*;
import com.carl.sso.support.validate.exception.ValidateSenderException;

/**
 * 默认的校验服务
 *
 * @author Carl
 * @date 2017/11/2
 * @since 2.3.8
 */
public class DefaultValidateService<K extends Informative, T extends Credential, O extends ValidateCredential> implements IValidateService<T, O> {
    private InformativeGenerator<K, T> generator;
    private IStore<K, T> store;
    private ISender<K> sender;
    private IValidator<O> validator;

    public DefaultValidateService(InformativeGenerator<K, T> generator, IStore<K, T> store, ISender<K> sender, IValidator<O> validator) {
        this.generator = generator;
        this.store = store;
        this.sender = sender;
        this.validator = validator;
    }

    public InformativeGenerator<K, T> getGenerator() {
        return generator;
    }

    public IStore<K, T> getStore() {
        return store;
    }

    public ISender<K> getSender() {
        return sender;
    }

    public IValidator<O> getValidator() {
        return validator;
    }

    @Override
    public void send(T t) throws ValidateSenderException {
        K informative = getGenerator().generate(t);
        getSender().send(informative);
        getStore().save(informative);
    }

    @Override
    public ValidateResult validate(O o) {
        return getValidator().identify(o);
    }
}
