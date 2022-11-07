package com.github.bjlhx15.common.loadbalance;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance001Random
 * @description 随机算法
 * @since 2022/11/7 9:59 下午
 */
public class LoadBalance001Random extends AbstractLoadBalance{

    public LoadBalance001Random(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(LoadBalanceContext contex ) {
        Random random = ThreadLocalRandom.current();
        int nextIndex = random.nextInt(servers.size());
        return servers.get(nextIndex);
    }

}