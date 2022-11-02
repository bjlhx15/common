package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.mockito.Mockito.*;

public class LoadBalance004RoundRobbinWeightTest {
    @Mock
    AtomicInteger requestCount;
    @Mock
    List<Server> servers;

    LoadBalance004RoundRobbinWeight loadBalance;

    @Before
    public void setUp() {
        servers = Arrays.asList(new Server("111", "11111", 1),
                new Server("222", "22222", 2),
                new Server("333", "33333", 7)
        );

        loadBalance = new LoadBalance004RoundRobbinWeight(servers);
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