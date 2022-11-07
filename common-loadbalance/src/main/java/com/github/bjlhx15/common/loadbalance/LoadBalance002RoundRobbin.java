package com.github.bjlhx15.common.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance002RoundRobbin
 * @description 轮训算法
 * @since 2022/11/7 9:59 下午
 */
public class LoadBalance002RoundRobbin extends AbstractLoadBalance {

    /**
     * 位移指针
     *
     * @since 0.0.1
     */
    private final AtomicLong indexHolder = new AtomicLong();

    public LoadBalance002RoundRobbin(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(LoadBalanceContext contex) {
        long index = indexHolder.getAndIncrement();
        int actual = (int) (index % servers.size());
        return servers.get(actual);
    }

}
