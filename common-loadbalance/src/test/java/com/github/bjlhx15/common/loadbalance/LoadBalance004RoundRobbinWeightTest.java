package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LoadBalance004RoundRobbinWeightTest {
    @Mock
    AtomicInteger requestCount;
    @Mock
    List<Server> servers;

    LoadBalance004RoundRobbinWeight loadBalance;

    @Before
    public void setUp() {
        List<Server> servers = Arrays.<Server>
                asList(new Server("111", 1, 1)
                , new Server("222", 2, 2)
                , new Server("333", 3, 3)
        );
        loadBalance = new LoadBalance004RoundRobbinWeight(servers);
    }


    @Test
    public void testSelect() throws Exception {

        for (int i = 0; i < 18; i++) {
            Server result = loadBalance.select();
            System.out.println(JSONObject.toJSONString(result));
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme