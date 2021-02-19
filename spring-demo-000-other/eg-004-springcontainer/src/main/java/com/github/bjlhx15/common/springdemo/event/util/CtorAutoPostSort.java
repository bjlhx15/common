package com.github.bjlhx15.common.springdemo.event.util;

import com.github.bjlhx15.common.springdemo.event.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lihongxu6
 * @version 1.0
 * @className CtorAutoPostSort
 * @description TODO
 * @date 2021-01-11 20:23
 */
@Component
public class CtorAutoPostSort {
    public CtorAutoPostSort() {
        System.out.println("构造函数");
        System.out.println("构造函数:" + iUserService);

    }

    @Autowired(required = false)
    IUserService iUserService;

    @PostConstruct
    public void init() {

        System.out.println("PostConstruct");
        System.out.println("PostConstruct:" + iUserService);
    }

    {
        System.out.println("构造代码块");
        System.out.println("构造代码块:" + iUserService);
    }

    static {
        System.out.println("静态代码块");
        System.out.println("静态代码块:不能获取普通属性");
    }
}
