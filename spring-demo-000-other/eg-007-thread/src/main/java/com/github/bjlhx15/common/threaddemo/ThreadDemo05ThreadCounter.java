package com.github.bjlhx15.common.threaddemo;

import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo05ThreadCounter extends Thread {
    final static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        try {
            while (true) {
                ThreadDemo05ThreadCounter threadDemo05ThreadCounter = new ThreadDemo05ThreadCounter();
                threadDemo05ThreadCounter.setPriority(5);
                threadDemo05ThreadCounter.start();
//                threadDemo05ThreadCounter.interrupted();
            }
        } catch (Throwable e) {
            System.out.println("counter:" + counter.get());
//            Thread.sleep(2000);
//            TimeUnit.MICROSECONDS.sleep(2000);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("counter:" + counter.getAndIncrement());
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
