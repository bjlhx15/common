package com.github.bjlhx15.common.springdemo.event.controller;

import com.github.bjlhx15.common.springdemo.event.domain.auto.TestJoinBean;
import com.github.bjlhx15.common.springdemo.event.domain.auto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserController
 * @description TODO
 * @date 2021-01-07 09:26
 */
@RestController
@RequestMapping("/user/get")
public class UserGet2Controller {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private GenericApplicationContext gac;

    @ResponseBody
    @RequestMapping("/selfReg")
    public ResponseEntity currBeanW(User record) {

        System.out.println("------------------注入前-----------------------");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("------------------注入后-----------------------");
        gac.registerBean(TestJoinBean.class);
        TestJoinBean bean = context.getBean(TestJoinBean.class);

        String[] beanDefinitionNames2 = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames2) {
            System.out.println(beanDefinitionName);
        }
        return ResponseEntity.ok("ok");
    }
}
