package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class LoadBalanceRoundRobbinTest {
    @Mock
    AtomicLong indexHolder;
    @Mock
    List<Server> servers;
    LoadBalance002RoundRobbin loadBalance;

    @Before
    public void setUp() {
        servers = Arrays.asList(new Server("111", "11111"), new Server("222", "22222"));

        loadBalance = new LoadBalance002RoundRobbin(servers);
    }


    @Test
    public void testSelect() throws Exception {
        for (int i = 0; i < 10; i++) {
            Server result = loadBalance.select(new ILoadBalanceContext());
            System.out.println(JSONObject.toJSONString(result));
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme