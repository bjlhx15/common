package com.github.bjlhx15.common.springdemo.event.jdkevent;

import java.util.EventObject;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSendEvent
 * @description TODO
 * @date 2020-10-02 11:12
 */
public class MailSendJdkEvent extends EventObject {
    private String to;  //目的地

    public MailSendJdkEvent(Object source, String to) {
        super(source);
        this.to = to;
    }

    public String getTo() {
        return this.to;
    }
}
