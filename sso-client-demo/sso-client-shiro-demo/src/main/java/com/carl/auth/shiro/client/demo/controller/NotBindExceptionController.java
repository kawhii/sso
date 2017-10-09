/*
 * 版权所有.(c)2008-2017. 卡尔科技工作室
 */

package com.carl.auth.shiro.client.demo.controller;

import com.carl.auth.shiro.client.demo.exception.NotBindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *
 * @author Carl
 * @date 2017/10/9
 * @since 1.5.0
 */
@ControllerAdvice
public class NotBindExceptionController {

    /**
     * 抛出未绑定异常时进行转发页面处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotBindException.class)
    public ModelAndView notBindHandler(NotBindException e) {
        return new ModelAndView("redirect:" + e.getRedirectUrl());
    }
}
