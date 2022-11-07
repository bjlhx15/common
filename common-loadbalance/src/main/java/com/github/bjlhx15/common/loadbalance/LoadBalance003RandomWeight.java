package com.github.bjlhx15.common.loadbalance;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance003RandomWeight
 * @description 随机权重
 * @since 2022/11/7 9:59 下午
 */
public class LoadBalance003RandomWeight extends AbstractLoadBalance {
    Random random = ThreadLocalRandom.current();

    public LoadBalance003RandomWeight(List<Server> servers) {
        super(servers);
    }

    @Override
    protected Server doSelect(LoadBalanceContext contex) {
        //计算总权值
        int weightTotal = servers.stream().mapToInt(p -> p.getWeight()).sum();
        //总权重范围内随机生成一个索引
        int index = random.nextInt(weightTotal);
//        System.out.println("随机索引：" + index);
        Server targetServer = null;
        for (Server server : servers) {
            int weight = server.getWeight();
            //如果权重值大于产生的随机数，则代表此次随机分配的应该落入该节点
            if (weight > index) {
                //直接返回该节点
                targetServer = server;
                break;
            }
            //否则，索引 = 当前索引 - 当前服务的权重
            // 如果当前节点的权重值小于随机索引，则用随机索引减去当前节点的权重值，
            // 继续循环权重列表，与其他的权重值进行对比，
            // 最终该请求总会落入到某个IP的权重值范围内
            index = index - weight;
//            System.out.println("一次随机索引：" + index);
        }

        return targetServer;
    }

}