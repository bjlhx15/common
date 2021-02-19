package com.github.bjlhx15.common.springdemo.event.controller;

import com.github.bjlhx15.common.springdemo.event.domain.auto.TestJoinBean;
import com.github.bjlhx15.common.springdemo.event.service.TestSonUtil;
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
@RequestMapping("/testson/")
public class TestSonController {

    @Autowired
    private TestSonUtil testSonUtil;

    @ResponseBody
    @RequestMapping("/re0")
    public ResponseEntity re0() {
        testSonUtil.handle();

        return ResponseEntity.ok("ok");
    }


}
