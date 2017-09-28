/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.sso.client.demo;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Carl
 * @date 2017/9/28
 * @since 1.5.0
 */
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean matches(String url) {
        logger.debug("访问路径：" + url);
        return url.contains("zhangsan.jsp");
    }

    @Override
    public void setPattern(String pattern) {

    }
}
