package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance001RandomTest
 * @description TODO
 * @since 2022/11/7 10:03 下午
 */
public class LoadBalance001RandomTest {
    @Mock
    List<Server> servers;
    ILoadBalance loadBalanceRandom;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servers = Arrays.<Server>
                asList(new Server("111", 1, 1)
                , new Server("222", 2, 2)
                , new Server("333", 3, 3)
        );
        loadBalanceRandom = new LoadBalance001Random(servers);
    }


    @Test
    public void testSelect() throws Exception {
        for (int i = 0; i < 10; i++) {
            Server result = loadBalanceRandom.select();
            System.out.println(JSONObject.toJSONString(result));
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme