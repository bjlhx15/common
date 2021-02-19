package com.github.bjlhx15.disruptor.demo.base.example;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

/**
 * 值+10
 */
public class C11EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println("EventHandler");
        System.out.println(System.currentTimeMillis() + "：threadid:"+Thread.currentThread().getId()+": c1-1 consumer finished.number=" + number);
        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void onEvent(LongEvent event) throws Exception {
        long number = event.getNumber();
        number += 10;
        System.out.println("WorkHandler");
        System.out.println(System.currentTimeMillis() + "：threadid:"+Thread.currentThread().getId()+ ": c1-1 consumer finished.number=" + number);
    }
}