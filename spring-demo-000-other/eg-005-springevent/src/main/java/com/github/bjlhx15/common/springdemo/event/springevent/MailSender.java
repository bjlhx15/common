package com.github.bjlhx15.common.springdemo.event.springevent;

import com.github.bjlhx15.common.springdemo.event.jdkevent.MailManager;
import com.github.bjlhx15.common.springdemo.event.jdkevent.MailSendJdkEvent;
import com.github.bjlhx15.common.springdemo.event.jdkevent.MailSendJdkEventListenerDb;
import com.github.bjlhx15.common.springdemo.event.jdkevent.MailSendJdkEventListenerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Service;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSender
 * @description TODO
 * @date 2020-10-02 11:13
 */
@Service
public class MailSender {
    @Autowired
    private ApplicationContext applicationContext;  //容器事件由容器触发

    public void sendMail(String to) {
        System.out.println("MailSender开始发送邮件");
        MailSendEvent event = new MailSendEvent(applicationContext,to);
        applicationContext.publishEvent(event);
        System.out.println("over");
    }
}
