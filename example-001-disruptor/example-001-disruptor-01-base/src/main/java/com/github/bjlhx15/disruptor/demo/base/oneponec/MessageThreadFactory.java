package com.github.bjlhx15.disruptor.demo.base.oneponec;

import java.util.concurrent.ThreadFactory;

/**
 * 消费者线程工厂类
 */
public class MessageThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"Simple Disruptor Test Thread");
    }
}
