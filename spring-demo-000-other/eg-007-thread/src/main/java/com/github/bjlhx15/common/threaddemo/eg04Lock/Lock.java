package com.github.bjlhx15.common.threaddemo.eg04Lock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author lihongxu6
 * @version 1.0
 * @className Lock
 * @description TODO
 * @date 2021-02-18 15:38
 */
public interface Lock {

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();
}
