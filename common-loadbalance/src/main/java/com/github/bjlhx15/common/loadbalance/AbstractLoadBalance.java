package com.github.bjlhx15.common.loadbalance;

import java.util.List;

/**
 * @author lihongxu
 * @version 1.0
 * @className AbstractLoadBalance
 * @description 负载抽象类
 * @since 2022/11/7 9:59 下午
 */
public abstract class AbstractLoadBalance implements ILoadBalance {
    public List<Server> servers;

    public AbstractLoadBalance(List<Server> servers) {
        this.servers = servers;
    }

    protected abstract Server doSelect(LoadBalanceContext contex);

    @Override
    public Server select(LoadBalanceContext contex) {
        return this.doSelect(contex);
    }
}
