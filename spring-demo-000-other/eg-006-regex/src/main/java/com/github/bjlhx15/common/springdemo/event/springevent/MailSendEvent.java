package com.github.bjlhx15.common.springdemo.event.springevent;

import org.springframework.context.ApplicationEvent;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSendEvent
 * @description TODO
 * @date 2021-01-10 04:47
 */
public class MailSendEvent extends ApplicationEvent {

    private String to;  //目的地
    public MailSendEvent(Object source,String to) {
        super(source);
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }
}
