package com.github.bjlhx15.common.springdemo.springbootjunit.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.springbootjunit.DemoApplication;
import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import com.github.bjlhx15.common.springdemo.springbootjunit.repository.auto.UserMapper;
import com.github.bjlhx15.common.springdemo.springbootjunit.service.IDepartService;
import com.github.bjlhx15.common.springdemo.springbootjunit.service.IUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserServiceImplTest
 * @description TODO
 * @date 2021-01-07 15:41
 */
@RunWith(SpringRunner.class)//调用Spring单元测试类
@ContextConfiguration(locations = {"classpath*:spring-config-mybatis-jd-dev.xml"})
public class UserServiceImplTest02MockData {

    @Mock
    private IDepartService departService;

    @Mock
    private UserMapper mapper ;

    @InjectMocks
    private UserServiceImpl service;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insertUserAndDepart() {
        User user = new User();
        user.setName("lhx");
        user.setAge(202);
        String userStr = JSON.toJSONString(user);
        when(service.insertUser(user))
                .thenReturn(1);
        when(departService.insert(user))
                .thenReturn(1);
        int i = service.insertUserAndDepart(user);
        Assert.assertEquals(2,i);
    }


    @Test
    public void insertUser() {

        User user = new User();
        user.setName("lhx");
        user.setAge(202);

        String userStr = JSON.toJSONString(user);
        when(service.insertUser(user))
                .thenReturn(1);
        int i = service.insertUser(user);
        Assert.assertEquals(1,i);
    }
}