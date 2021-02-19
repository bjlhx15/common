package com.github.bjlhx15.common.threaddemo.eg05threadpool;

/**
 * @author lihongxu6
 * @version 1.0
 * @className ThreadFactory
 * @description TODO
 * @date 2021-02-18 17:49
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
