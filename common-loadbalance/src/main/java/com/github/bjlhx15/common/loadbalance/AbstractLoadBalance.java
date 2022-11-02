package com.github.bjlhx15.common.loadbalance;

import java.util.List;

public abstract class AbstractLoadBalance implements ILoadBalance {
    public List<Server> servers;

    public AbstractLoadBalance(List<Server> servers) {
        this.servers = servers;
    }

    protected abstract Server doSelect(ILoadBalanceContext context);

    @Override
    public Server select(ILoadBalanceContext context) {
        return this.doSelect(context);
    }
}
