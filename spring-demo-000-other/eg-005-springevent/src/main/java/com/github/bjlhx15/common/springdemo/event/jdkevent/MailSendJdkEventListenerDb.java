package com.github.bjlhx15.common.springdemo.event.jdkevent;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSendEvent
 * @description TODO
 * @date 2020-10-02 11:12
 */
public class MailSendJdkEventListenerDb implements MailSendJdkEventListener {
    @Override
    public void MailSendJdkEventListener(MailSendJdkEvent source) {
        System.out.println("write DB:"+source.getTo());
    }
}
