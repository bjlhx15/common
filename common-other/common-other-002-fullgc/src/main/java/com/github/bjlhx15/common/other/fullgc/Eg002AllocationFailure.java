package com.github.bjlhx15.common.other.fullgc;

import java.util.concurrent.TimeUnit;

public class Eg002AllocationFailure {
    public static void main(String[] args) throws InterruptedException {
        byte[] bigObj1 = new byte[1  s024 * 1024 * 160];
        TimeUnit.SECONDS.sleep(1);
        byte[] bigObj2 = new byte[1024 * 1024 * 70];
    }
}
