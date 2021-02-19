package com.github.bjlhx15.common.threaddemo.eg02singlethread;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

/**
 * @author lihongxu6
 * @version 1.0
 * @className EventQueue
 * @description TODO
 * @date 2021-02-18 14:33
 */
public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            while (true) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "producer").start();

        new Thread(() -> {
            while (true) {
                eventQueue.take();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }
}
