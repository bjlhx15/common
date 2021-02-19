package com.github.bjlhx15.common.threaddemo.eg05threadpool;

/**
 * @author lihongxu6
 * @version 1.0
 * @className DenyPolicy
 * @description TODO
 * @date 2021-02-18 17:50
 */
@FunctionalInterface
public interface DenyPolicy {
    void reject(Runnable runnable, ThreadPool threadPool);

    //直接丢弃
    class DiscardDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }

    //拒绝
    class AbortDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RuntimeException("拒绝");

        }
    }

    //提交给任务所在线程执行任务
    class RunnerDenyPolicy implements DenyPolicy {
        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
