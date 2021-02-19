package com.github.bjlhx15.common.springdemo.event.jdkevent;

import org.junit.Test;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSenderJdkTest
 * @description TODO
 * @date 2021-01-09 21:47
 */
public class MailSenderJdkTest {

    @Test
    public void sendMail() {
        MailSenderJdk mailSenderJdk=new MailSenderJdk();
        mailSenderJdk.sendMail("bjlhx15@163.com");
    }
}