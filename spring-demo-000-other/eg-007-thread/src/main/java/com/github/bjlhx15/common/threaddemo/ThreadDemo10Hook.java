package com.github.bjlhx15.common.threaddemo;

import java.util.concurrent.TimeUnit;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo10Hook
 * @description TODO
 * @date 2021-02-18 17:00
 */
public class ThreadDemo10Hook {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("第1个hook,start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第1个hook,end");
        }));
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("第2个hook,start");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第2个hook,end");
        }));
        System.out.println("程序结束。");
    }
}
