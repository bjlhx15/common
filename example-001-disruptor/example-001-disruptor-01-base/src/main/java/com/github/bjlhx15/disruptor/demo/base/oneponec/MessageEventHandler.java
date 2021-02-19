package com.github.bjlhx15.disruptor.demo.base.oneponec;

import com.lmax.disruptor.EventHandler;

/**
 * 消息事件处理类，这里只打印消息
 */
public class MessageEventHandler implements EventHandler<MessageEvent> {
    @Override
    public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
        System.out.println(messageEvent.getMessage());
    }
}
