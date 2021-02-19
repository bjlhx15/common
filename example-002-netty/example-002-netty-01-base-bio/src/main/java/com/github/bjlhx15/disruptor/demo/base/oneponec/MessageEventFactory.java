package com.github.bjlhx15.disruptor.demo.base.oneponec;

import com.lmax.disruptor.EventFactory;

/**
 * 消息事件工厂类
 */
public class MessageEventFactory implements EventFactory<MessageEvent> {
    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}