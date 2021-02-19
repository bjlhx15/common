package com.github.bjlhx15.disruptor.demo.base.oneponec;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorTest {


    public static void main(String[] args) {
        String message = "Hello Disruptor!";
        int ringBufferSize = 1024;//必须是2的N次方
        Disruptor<MessageEvent> disruptor = new Disruptor<MessageEvent>(new MessageEventFactory(),ringBufferSize,new MessageThreadFactory(), ProducerType.SINGLE,new BlockingWaitStrategy());
        disruptor.handleEventsWith(new MessageEventHandler());
        disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        MessageEventProducer producer = new MessageEventProducer(ringBuffer);
        producer.onData(message);
    }
}
