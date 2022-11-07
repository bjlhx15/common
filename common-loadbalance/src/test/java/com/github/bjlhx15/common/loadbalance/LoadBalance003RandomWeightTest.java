package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

public class LoadBalance003RandomWeightTest {
    @Mock
    List<Server> servers;
    @InjectMocks
    LoadBalance003RandomWeight loadBalance;

    @Before
    public void setUp() {
        List<Server> servers = Arrays.<Server>
                asList(new Server("111", 1, 1)
                , new Server("222", 2, 2)
                , new Server("333", 3, 3)
        );
        loadBalance = new LoadBalance003RandomWeight(servers);
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