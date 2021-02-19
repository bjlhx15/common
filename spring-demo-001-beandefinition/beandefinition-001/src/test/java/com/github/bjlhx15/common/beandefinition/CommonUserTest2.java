package com.github.bjlhx15.common.beandefinition;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.atomic.AtomicBoolean;

public class CommonUserTest2 {


    @Test
    public void saveOrder() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        CommonUser commonUser = (CommonUser) context.getBean("user");
        System.out.println(commonUser.getUserName() + "----" + commonUser.getEmail());
    }

    public static void main(String[] args) {

        // 启动标示
        AtomicBoolean started = new AtomicBoolean(false);
        if (started.compareAndSet(true, false)) {
            System.out.println(1);
        }
    }

}