package com.github.bjlhx15.common.loadbalance;

public interface ILoadBalance {
    Server select(final ILoadBalanceContext context);
}
