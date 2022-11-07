package com.github.bjlhx15.common.loadbalance;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance004RoundRobbinWeight
 * @description 轮询权重
 * @since 2022/11/7 9:59 下午
 */
public class LoadBalance004RoundRobbinWeight extends AbstractLoadBalance {


    private final AtomicLong indexHolder = new AtomicLong();

    public LoadBalance004RoundRobbinWeight(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(LoadBalanceContext contex) {
        int weight = servers.stream().mapToInt(p -> p.getWeight()).sum();
        long number = (indexHolder.getAndIncrement()) % weight;
        for (Server server : servers) {
            if (server.getWeight() > number) {
                return server;
            }
            number = number - server.getWeight();
        }
        return null;
    }


}
