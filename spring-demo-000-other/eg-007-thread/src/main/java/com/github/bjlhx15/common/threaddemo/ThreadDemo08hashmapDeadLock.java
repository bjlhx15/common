package com.github.bjlhx15.common.threaddemo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadDemo01
 * @description TODO
 * @date 2021-02-13 11:00
 */
public class ThreadDemo08hashmapDeadLock {
    private final HashMap<String, String> map = new HashMap<>();

    public void add(String key, String value) {
        map.put(key, value);
    }

    public static void main(String[] args) {
        final ThreadDemo08hashmapDeadLock hashmapDeadLock = new ThreadDemo08hashmapDeadLock();
        for (int x = 0; x < 2; x++) {
            new Thread(() -> {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    hashmapDeadLock.add(String.valueOf(i), String.valueOf(i));

                }
            }).start();
        }
    }
}
