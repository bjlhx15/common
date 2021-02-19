package com.github.bjlhx15.common.springdemo.event.domain.auto;

import com.github.bjlhx15.common.springdemo.event.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lihongxu6
 * @version 1.0
 * @className TestJoinBean
 * @description TODO
 * @date 2021-01-09 13:48
 */
public class TestJoinBean {

    @Autowired
    IUserService userService;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
