package com.github.bjlhx15.common.springdemo.event.springevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSender
 * @description TODO
 * @date 2020-10-02 11:13
 */


@Service
public class MailSenderIface implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    public void sendMail(String to) {
        System.out.println("MailSender开始发送邮件");
        MailSendEvent event = new MailSendEvent(this, to);
        applicationEventPublisher.publishEvent(event);
        System.out.println("over");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
