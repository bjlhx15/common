package com.github.bjlhx15.common.springdemo.event.service;

import com.github.bjlhx15.common.springdemo.event.util.SpringBeanUtil;

/**
 * @author lihongxu6
 * @version 1.0
 * @className AbstractTestSon
 * @description TODO
 * @date 2021-01-12 10:54
 */
public abstract class AbstractTestSon {
    public void handle(){
        ITestSonService bean = SpringBeanUtil.getBean(ITestSonService.class);
        bean.get();
        son();
    }

    public abstract void son();
}
