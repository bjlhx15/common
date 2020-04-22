package com.github.bjlhx15.common.beandefinition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CommonUserTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        CommonUser user = (CommonUser) context.getBean("user");
        System.out.println(user.getUserName() + "----" + user.getEmail());
    }
}