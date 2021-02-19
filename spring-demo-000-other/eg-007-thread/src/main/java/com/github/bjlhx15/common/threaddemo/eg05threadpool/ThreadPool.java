package com.github.bjlhx15.common.threaddemo.eg05threadpool;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadPool
 * @description TODO
 * @date 2021-02-18 17:45
 */
public interface ThreadPool {
    void execute(Runnable runnable);

    void shutdown();

    int getInitSize();

    int getCoreSize();

    int getMaxSize();

    int getQueueSize();

    //活跃线程数
    int getActiveCount();

    //线程池是否被销毁
    boolean isShutdown();
}
