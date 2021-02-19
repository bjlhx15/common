package com.github.bjlhx15.common.springdemo.event.controller;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.event.domain.auto.User;
import com.github.bjlhx15.common.springdemo.event.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserController
 * @description TODO
 * @date 2021-01-07 09:26
 */
@RestController
@RequestMapping("/user/get")
public class UserGetController {
    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @ResponseBody
    @RequestMapping("/insert")
    public ResponseEntity insert(User record) {
//          SpringBeanUtil.getAllBean();


        System.out.println(JSON.toJSONString(record));
        return ResponseEntity.ok(userService.insertUser(record));
    }

    @ResponseBody
    @RequestMapping("/currBean")
    public ResponseEntity currBean(User record) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }


        System.out.println(JSON.toJSONString(record));
        return ResponseEntity.ok(userService.insertUser(record));
    }

    @ResponseBody
    @RequestMapping("/currBeanW")
    public ResponseEntity currBeanW(User record) {
        String[] beanDefinitionNames = webApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }


        System.out.println(JSON.toJSONString(record));
        return ResponseEntity.ok(userService.insertUser(record));
    }
}
