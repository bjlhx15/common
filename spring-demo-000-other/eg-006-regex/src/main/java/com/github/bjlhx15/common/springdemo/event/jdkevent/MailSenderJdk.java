package com.github.bjlhx15.common.springdemo.event.jdkevent;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailSender
 * @description TODO
 * @date 2020-10-02 11:13
 */
public class MailSenderJdk {

    public void sendMail(String to) {
        System.out.println("MailSender开始发送邮件");

        MailManager manager = new MailManager();
        manager.adListener(new MailSendJdkEventListenerDb());// 给增加监听器
        manager.adListener(new MailSendJdkEventListenerLog());// 给增加监听器
        // 触发
        MailSendJdkEvent event = new MailSendJdkEvent(this, to);
        manager.notifyListeners(event);
        System.out.println("over");
    }
}
