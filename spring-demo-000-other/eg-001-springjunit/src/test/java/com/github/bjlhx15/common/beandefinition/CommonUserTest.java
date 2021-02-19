package com.github.bjlhx15.common.beandefinition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"classpath*:spring.xml"}) //加载配置文件
public class CommonUserTest {

    @Autowired
    CommonUser commonUser;

    @Test
    public void saveOrder() {
        System.out.println(commonUser.getUserName() + "----" + commonUser.getEmail());
    }

}