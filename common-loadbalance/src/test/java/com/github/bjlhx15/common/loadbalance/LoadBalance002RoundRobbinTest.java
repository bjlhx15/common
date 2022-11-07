package com.github.bjlhx15.common.loadbalance;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance002RoundRobbinTest
 * @description TODO
 * @since 2022/11/7 10:25 下午
 */
public class LoadBalance002RoundRobbinTest {
    @Mock
    AtomicLong indexHolder;
    @Mock
    List<Server> servers;
    @InjectMocks
    LoadBalance002RoundRobbin loadBalance002RoundRobbin;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Server> servers = Arrays.<Server>
                asList(new Server("111", 1, 1)
                , new Server("222", 2, 2)
                , new Server("333", 3, 3)
        );
        loadBalance002RoundRobbin=new LoadBalance002RoundRobbin(servers);
    }

    @Test
    public void testSelect() throws Exception {
        for (int i = 0; i < 9; i++) {
            Server result = loadBalance002RoundRobbin.select();
            System.out.println(JSONObject.toJSONString(result));
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme