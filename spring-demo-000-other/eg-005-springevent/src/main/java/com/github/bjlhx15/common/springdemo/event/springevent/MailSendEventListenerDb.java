package com.github.bjlhx15.common.springdemo.event.springevent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSendEvent
 * @description TODO
 * @date 2020-10-02 11:12
 */
@Component
public class MailSendEventListenerDb implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent source) {
        System.out.println("write DB:" + source.getTo());
    }
}
