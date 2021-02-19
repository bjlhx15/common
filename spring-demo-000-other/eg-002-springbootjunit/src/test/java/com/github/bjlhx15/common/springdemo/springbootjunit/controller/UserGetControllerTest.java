package com.github.bjlhx15.common.springdemo.springbootjunit.controller;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.springbootjunit.DemoApplication;
import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserGetControllerTest
 * @description TODO
 * @date 2021-01-07 12:34
 */
@RunWith(SpringRunner.class)//调用Spring单元测试类
@SpringBootTest(classes = DemoApplication.class) //加载Spring配置文件
//@ContextConfiguration(locations = {"classpath*:spring-*.xml"})      //加载Spring配置文件
@WebAppConfiguration //调用Java Web组件，如自动注入ServletContext Bean等
public class UserGetControllerTest {

    @Autowired
    protected WebApplicationContext context;

    private MockMvc mockMvc;        //SpringMVC提供的Controller测试类

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(context).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void insert() throws Exception {

        User user = new User();
        user.setName("lhx");
        user.setAge(20);

        String userStr = JSON.toJSONString(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/postform/insert")
                .param("name", "lhx")
                .param("age", "30")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}