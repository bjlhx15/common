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

import static org.mockito.Mockito.*;

/**
 * @author lihongxu
 * @version 1.0
 * @className LoadBalance005SmoothRoundRobbinWeightTest
 * @description TODO
 * @since 2022/11/7 10:48 下午
 */
public class LoadBalance005SmoothRoundRobbinWeightTest {
    @Mock
    List<Server> servers;
    @InjectMocks
    LoadBalance005SmoothRoundRobbinWeight loadBalance005SmoothRoundRobbinWeight;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Server> servers = Arrays.<Server>
                asList(new Server("111", 1, 1)
                , new Server("222", 2, 2)
                , new Server("333", 3, 3)
        );
        loadBalance005SmoothRoundRobbinWeight = new LoadBalance005SmoothRoundRobbinWeight(servers);
    }


    @Test
    public void testSelect() throws Exception {
        for (int i = 0; i < 18; i++) {
            Server result = loadBalance005SmoothRoundRobbinWeight.select();
            System.out.println(JSONObject.toJSONString(result));
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme