package com.github.bjlhx15.common.loadbalance;

import java.util.List;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance005SmoothRoundRobbinWeight
 * @description TODO
 * @since 2022/11/7 10:44 下午
 */
public class LoadBalance005SmoothRoundRobbinWeight extends AbstractLoadBalance {
    public LoadBalance005SmoothRoundRobbinWeight(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(LoadBalanceContext contex) {
        Server maxSmoothWeight = null;
        int weight = servers.stream().mapToInt(Server::getWeight).sum();

        for (Server server : servers) {
            if (maxSmoothWeight == null || server.getCurrentWeight() > maxSmoothWeight.getCurrentWeight()) {
                maxSmoothWeight = server;
            }
        }
        assert maxSmoothWeight != null;
        maxSmoothWeight.setCurrentWeight(maxSmoothWeight.getCurrentWeight() - weight);
        for (Server server : servers) {
            server.setCurrentWeight(server.getCurrentWeight() + server.getWeight());
        }

        return maxSmoothWeight;
    }
}
