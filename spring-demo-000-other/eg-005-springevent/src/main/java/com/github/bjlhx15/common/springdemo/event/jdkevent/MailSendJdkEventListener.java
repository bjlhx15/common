package com.github.bjlhx15.common.springdemo.event.jdkevent;

import java.util.EventListener;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSendEvent
 * @description TODO
 * @date 2020-10-02 11:12
 */
public interface MailSendJdkEventListener extends EventListener {
    void MailSendJdkEventListener(MailSendJdkEvent source);
}
