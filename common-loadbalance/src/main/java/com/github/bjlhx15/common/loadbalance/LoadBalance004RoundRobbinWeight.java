package com.github.bjlhx15.common.loadbalance;


import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class LoadBalance004RoundRobbinWeight extends AbstractLoadBalance {
//    private static AtomicInteger requestCount = new AtomicInteger(0);


    private int currentIndex = -1;// 上一次选择的服务器
    private int currentWeight = 0;// 当前调度的权值
    private int maxWeight = 0; // 最大权重
    private int gcdWeight = 0; //所有服务器权重的最大公约数
    private int totalServer = 0; //服务器数量
//    private List<Server> serverList; //服务器集合
    int weightTotal = 0;

    public LoadBalance004RoundRobbinWeight(List<Server> servers) {
        super(servers);
        for (Server server : servers) {
            weightTotal += server.getWeight();
        }
        totalServer = servers.size();

        currentIndex = totalServer - 1;

        maxWeight = maxWeight();

        gcdWeight = serverGcd();
    }

    @Override
    protected Server doSelect(ILoadBalanceContext context) {
        while (true) {
            currentIndex = (currentIndex + 1) % totalServer;
            if (currentIndex == 0) {
                currentWeight = currentWeight - gcdWeight;
                if (currentWeight <= 0) {
                    currentWeight = maxWeight;
                    if (currentWeight == 0) {
                        return null;
                    }
                }
            }
            if (servers.get(currentIndex).getWeight() >= currentWeight) {
                return servers.get(currentIndex);
            }
        }
    }


    /**
     * 返回所有服务器的权重的最大公约数
     *
     * @return
     */
    private int serverGcd() {
        int comDivisor = 0;
        for (int i = 0; i < servers.size() - 1; i++) {
            if (comDivisor == 0) {
                comDivisor = gcd(servers.get(i).getWeight(), servers.get(i + 1).getWeight());
            } else {
                comDivisor = gcd(comDivisor, servers.get(i + 1).getWeight());
            }
        }
        return comDivisor;
    }

    /**
     * 获得服务器中的最大权重
     *
     * @return
     */
    private int maxWeight() {
        int max = servers.get(0).getWeight();
        int tmp;
        for (int i = 1; i < servers.size(); i++) {
            tmp = servers.get(i).getWeight();
            if (max < tmp) {
                max = tmp;
            }
        }
        return max;
    }

    /**
     * 求两个数的最大公约数 4和6最大公约数是2
     *
     * @param num1
     * @param num2
     * @return
     */
    private int gcd(int num1, int num2) {
        BigInteger i1 = new BigInteger(String.valueOf(num1));
        BigInteger i2 = new BigInteger(String.valueOf(num2));
        return i1.gcd(i2).intValue();
    }

}
