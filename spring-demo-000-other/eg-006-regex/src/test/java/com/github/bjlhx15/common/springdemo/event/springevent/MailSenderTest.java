package com.github.bjlhx15.common.springdemo.event.springevent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSenderTest
 * @description TODO
 * @date 2021-01-10 04:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderTest {

    @Autowired
    MailSender mailSender;
//    @Autowired
//    MailSenderIface mailSender;
    @Test
    public void sendMail() {
        mailSender.sendMail("bjlhx15@163.com");
    }
}