package com.github.bjlhx15.common.springdemo.event.jdkevent;

import java.util.Iterator;
import java.util.Vector;

/**
 * @author lihongxu6
 * @version 1.0
 * @className MailManager
 * @description TODO
 * @date 2021-01-09 21:37
 */
public class MailManager {
    private Vector listeners;

    /**
     * 添加事件
     *
     * @param listener
     */
    public void adListener(MailSendJdkEventListener listener) {
        if (listeners == null) {
            listeners = new Vector();
        }
        listeners.add(listener);
    }

    /**
     * 移除事件
     *
     * @param listener
     */
    public void removeListener(MailSendJdkEventListener listener) {
        if (listeners == null)
            return;
        listeners.remove(listener);
    }

    /**
     * 通知所有的Listener
     */
    public void notifyListeners(MailSendJdkEvent event) {
        if (listeners == null)
            return;
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            MailSendJdkEventListener listener = (MailSendJdkEventListener) iter.next();
            listener.MailSendJdkEventListener(event);
        }
    }
}
