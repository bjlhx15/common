package com.github.bjlhx15.boot2async;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AsyncHelloInterceptor implements AsyncHandlerInterceptor  {
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AsyncHandlerInterceptor :" + Thread.currentThread().getName() + "---afterConcurrentHandlingStarted-->" + request.getRequestURI());

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AsyncHandlerInterceptor :" + Thread.currentThread().getName() + "---preHandle-->" + request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("AsyncHandlerInterceptor :" + Thread.currentThread().getName() + "---postHandle-->" + request.getRequestURI());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AsyncHandlerInterceptor :" + Thread.currentThread().getName() + "---afterCompletion-->" + request.getRequestURI());
    }
}
