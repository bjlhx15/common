package com.github.bjlhx15.disruptor.demo.base.oneponec;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * 消息生产者类
 */
public class MessageEventProducer{
    private RingBuffer<MessageEvent> ringBuffer;

    public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 将接收到的消息输出到ringBuffer
     * @param message
     */
    public void onData(String message){
        EventTranslatorOneArg<MessageEvent,String> translator = new MessageEventTranslator();
        ringBuffer.publishEvent(translator,message);
    }
}
