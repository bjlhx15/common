package com.github.bjlhx15.common.springdemo.springbootjunit.controller;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.springbootjunit.DemoApplication;
import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserPostJsonControllerTest
 * @description TODO
 * @date 2021-01-07 12:24
 */
//@Profile({"jd","jd-dev","dev-fesco"})
@RunWith(SpringRunner.class)//调用Spring单元测试类
@SpringBootTest(classes = DemoApplication.class) //加载Spring配置文件
//@ContextConfiguration(locations = {"classpath*:spring-*.xml"})      //加载Spring配置文件
@WebAppConfiguration //调用Java Web组件，如自动注入ServletContext Bean等
@Transactional
@Rollback
public class UserPostJsonControllerTest {

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
        user.setAge(32);

        String userStr = JSON.toJSONString(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/postjson/insert")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(userStr)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}