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
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserServiceImplTest
 * @description TODO
 * @date 2021-01-07 15:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class) //加载Spring配置文件
@Transactional
@Rollback
public class UserServiceImplTest03PartMockPartReal {

    @Mock
    private IDepartService departService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    @InjectMocks
    private UserServiceImpl service;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(service, "departService", departService);
    }

    @Test
    public void insertUserAndDepart() {
        User user = new User();
        user.setName("lhx");
        user.setAge(202);

        when(departService.insert(user))
                .thenReturn(1);// 打桩
//        when(mapper.insert(user))
//                .thenReturn(1);

        int i = service.insertUserAndDepart(user);
        Assert.assertEquals(2, i);
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
        Assert.assertEquals(1, i);
    }
}