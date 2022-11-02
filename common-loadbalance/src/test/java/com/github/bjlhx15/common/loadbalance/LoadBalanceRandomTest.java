package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class LoadBalanceRandomTest {
    @Mock
    List<Server> servers;
    ILoadBalance loadBalanceRandom;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servers = Arrays.asList(new Server("111", "11111"), new Server("222", "22222"));

        loadBalanceRandom = new LoadBalance001Random(servers);
    }


    @Test
    public void testSelect() throws Exception {
        for (int i = 0; i < 10; i++) {
            Server result = loadBalanceRandom.select(new ILoadBalanceContext());
            System.out.println(JSONObject.toJSONString(result));
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme