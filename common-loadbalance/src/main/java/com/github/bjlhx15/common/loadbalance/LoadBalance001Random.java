package com.github.bjlhx15.common.loadbalance;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LoadBalance001Random extends AbstractLoadBalance{

    public LoadBalance001Random(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(ILoadBalanceContext context) {
        Random random = ThreadLocalRandom.current();
        int nextIndex = random.nextInt(servers.size());
        return servers.get(nextIndex);
    }

}