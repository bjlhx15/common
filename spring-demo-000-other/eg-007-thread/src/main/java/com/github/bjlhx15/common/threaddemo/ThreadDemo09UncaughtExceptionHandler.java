package com.github.bjlhx15.common.threaddemo;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo09UncaughtExceptionHandler {


    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occur execption.");
            e.printStackTrace();
        });

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
            }
            System.out.println(1 / 0);
        }, "Test-Thread");
        thread.start();
    }
}
