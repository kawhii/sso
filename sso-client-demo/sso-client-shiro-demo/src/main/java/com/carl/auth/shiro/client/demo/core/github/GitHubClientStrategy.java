/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.core.github;

import com.carl.auth.shiro.client.demo.core.ClientStrategy;
import com.carl.auth.shiro.client.demo.core.PrincipalBindResolver;
import io.buji.pac4j.subject.Pac4jPrincipal;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

/**
 * github处理策略
 *
 * @author Carl
 * @date 2017/10/8
 * @since 1.5.0
 */
public class GitHubClientStrategy implements ClientStrategy {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    //用户绑定决定
    private PrincipalBindResolver resolver;

    public GitHubClientStrategy(PrincipalBindResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public String name() {
        return "github";
    }

    @Override
    public void handle(JoinPoint joinPoint) throws Exception {
        //todo 未登录转发页面处理
        logger.warn("GitHub绑定跳转逻辑尚未处理。。。");
//        return new ModelAndView("redirect:bind/github");
    }

    @Override
    public boolean isBind(Pac4jPrincipal principal) {
        return resolver.isBind(principal.getProfile().getId());
    }
}
