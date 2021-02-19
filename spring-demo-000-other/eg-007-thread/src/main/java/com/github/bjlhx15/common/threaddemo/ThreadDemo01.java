package com.github.bjlhx15.common.threaddemo;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo01 {
    public static void main(String[] args) throws Exception {
//        Thread.State
//        Callable
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("aa");
            }
        };
//        t.setName();
        t.start();
        while (true) {
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
