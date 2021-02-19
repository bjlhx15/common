package com.github.bjlhx15.common.threaddemo.eg04Lock;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author lihongxu6
 * @version 1.0
 * @className BooleanLockTest
 * @description TODO
 * @date 2021-02-18 16:03
 */
public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread() + " get the lock");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final BooleanLockTest booleanLockTest = new BooleanLockTest();
        IntStream.range(1, 10)
                .mapToObj(p -> new Thread(() -> {
                    booleanLockTest.syncMethod();
                }))
                .forEach(Thread::start);
    }
}