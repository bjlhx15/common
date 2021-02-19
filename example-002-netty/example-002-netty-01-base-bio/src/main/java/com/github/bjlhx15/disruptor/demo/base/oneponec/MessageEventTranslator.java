package com.github.bjlhx15.disruptor.demo.base.oneponec;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * 消息转换类，负责将消息转换为事件
 */
public class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent,String> {
    @Override
    public void translateTo(MessageEvent messageEvent, long l, String s) {
        messageEvent.setMessage(s);
    }
}
