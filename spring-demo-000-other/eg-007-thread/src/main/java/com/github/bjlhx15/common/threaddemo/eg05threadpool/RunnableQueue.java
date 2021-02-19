package com.github.bjlhx15.common.threaddemo.eg05threadpool;

/**
 * @author lihongxu6
 * @version 1.0
 * @className RunnableQueue
 * @description TODO
 * @date 2021-02-18 17:48
 */
public interface RunnableQueue {
    void offer(Runnable runnable);

    Runnable take();

    int size();
}
