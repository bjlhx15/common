package com.github.bjlhx15.common.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LoadBalance002RoundRobbin extends AbstractLoadBalance {

    /**
     * 位移指针
     * @since 0.0.1
     */
    private final AtomicLong indexHolder = new AtomicLong();

    public LoadBalance002RoundRobbin(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(ILoadBalanceContext context) {
        long index = indexHolder.getAndIncrement();
        int actual = (int) (index % servers.size());
        return servers.get(actual);
    }

}
