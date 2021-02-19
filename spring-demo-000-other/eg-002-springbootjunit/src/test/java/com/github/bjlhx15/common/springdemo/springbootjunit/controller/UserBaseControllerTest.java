package com.github.bjlhx15.common.springdemo.springbootjunit.controller;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.springbootjunit.DemoApplication;
import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
//@Profile({"dev","dev-fesco"}) //环境变量
//SpringBoot1.4版本之前用的是SpringJUnit4ClassRunner.class
@RunWith(SpringRunner.class)
//SpringBoot1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
//@ContextConfiguration(locations = {"classpath*:spring-*.xml"})      //加载Spring配置文件 方式
@SpringBootTest(classes = DemoApplication.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
//配置事务的回滚,对数据库的增删改都会回滚, Spring框架中（Spring4.2以后），@TransactionConfiguration已经标注为过时的注解
@Rollback
@Transactional
public class UserBaseControllerTest {

    @Autowired
    protected WebApplicationContext context;

    private MockMvc mockMvc;        //SpringMVC提供的Controller测试类

    @Before
    public void setUp() throws Exception {
        // 实例化方式一、对于controller有依赖的 无法注入
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserPostJsonController()).build();
        // 实例化方式二、对于controller有依赖的 可以注入
         mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void insert() throws Exception {
        User user = new User();
        user.setName("lhx");
        user.setAge(20);
        String userStr = JSON.toJSONString(user);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/user/postjson/insert")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(userStr)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();  //将相应的数据转换为字符串
    }
}