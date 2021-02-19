package com.github.bjlhx15.common.springdemo.springbootjunit.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.springbootjunit.DemoApplication;
import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import com.github.bjlhx15.common.springdemo.springbootjunit.repository.auto.UserMapper;
import com.github.bjlhx15.common.springdemo.springbootjunit.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserServiceImplTest
 * @description TODO
 * @date 2021-01-07 15:41
 */
@RunWith(SpringRunner.class)//调用Spring单元测试类
@SpringBootTest(classes = DemoApplication.class) //加载Spring配置文件
public class UserServiceImplTest01RealData {

    @Autowired
    private IUserService service;

    @Test
    public void insertUser() {

        User user = new User();
        user.setName("lhx");
        user.setAge(202);

        service.insertUser(user);
    }
}