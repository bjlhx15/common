package com.github.bjlhx15.common.springdemo.event.controller;

import com.github.bjlhx15.common.springdemo.event.domain.auto.TestJoinBean;
import com.github.bjlhx15.common.springdemo.event.domain.auto.User;
import com.github.bjlhx15.common.springdemo.event.util.ManualRegistBeanUtil;
import com.github.bjlhx15.common.springdemo.event.util.ManualRegistBeanUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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
@RequestMapping("/register/")
public class RegisterBeanController {

    @Autowired
    private ManualRegistBeanUtil2 registerBeanUtil2;
    @Autowired
    private ApplicationContext context;

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @ResponseBody
    @RequestMapping("/re0")
    public ResponseEntity re0() {
        TestJoinBean bean = context.getBean(TestJoinBean.class);
        System.out.println(bean);
        System.out.println(bean.getUserService());

        return ResponseEntity.ok("ok");
    }

    @ResponseBody
    @RequestMapping("/re1")
    public ResponseEntity re1() {

        ManualRegistBeanUtil.registerBean(applicationContext, "test", TestJoinBean.class);

        TestJoinBean bean = context.getBean(TestJoinBean.class);
        System.out.println(bean);
        System.out.println(bean.getUserService());

        return ResponseEntity.ok("ok");
    }

    @ResponseBody
    @RequestMapping("/re2")
    public ResponseEntity re2() {

        registerBeanUtil2.registerBean(TestJoinBean.class);

        TestJoinBean bean = context.getBean(TestJoinBean.class);
        System.out.println(bean);
        System.out.println(bean.getUserService());

        return ResponseEntity.ok("ok");
    }
}
