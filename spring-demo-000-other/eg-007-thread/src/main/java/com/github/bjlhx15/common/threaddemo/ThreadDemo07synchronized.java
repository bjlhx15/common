package com.github.bjlhx15.common.threaddemo;

import java.util.concurrent.TimeUnit;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo07synchronized {
    public void method() {
        synchronized (this) {
            System.out.println("Method 1 start");
        }
    }
}
