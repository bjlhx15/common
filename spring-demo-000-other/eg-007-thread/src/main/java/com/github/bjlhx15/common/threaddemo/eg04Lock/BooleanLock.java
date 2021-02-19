package com.github.bjlhx15.common.threaddemo.eg04Lock;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * @author lihongxu6
 * @version 1.0
 * @className BooleanLock
 * @description TODO
 * @date 2021-02-18 15:37
 */
public class BooleanLock implements Lock {

    private Thread currentThread;

    private boolean locked = false;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                final Thread tempThead = currentThread();
                try {
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    this.wait();
                } catch (InterruptedException e) {
                    blockedList.remove(tempThead);
                    throw e;
                }
            }

            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills < 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;


                while (locked) {
                    if (remainingMills <= 0)
                        throw new TimeoutException("锁定时间获取异常");

                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
            }

            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == Thread.currentThread()) {
                this.locked = false;
                Optional.of(Thread.currentThread().getName() + " release the lock").ifPresent(p -> System.out.println(p));
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
