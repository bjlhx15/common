package com.github.bjlhx15.common.springdemo.event.service;

import com.github.bjlhx15.common.springdemo.event.util.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lihongxu6
 * @version 1.0
 * @className AbstractTestSon
 * @description TODO
 * @date 2021-01-12 10:54
 */
@Component
public class TestSonUtil {

    @Autowired
    ApplicationContext context;

    public void handle() {
        Map<String, AbstractTestSon> beansOfType = context.getBeansOfType(AbstractTestSon.class);
        for (Map.Entry<String, AbstractTestSon> seriveEntry : beansOfType.entrySet()) {
            System.out.println(seriveEntry.getKey());
            seriveEntry.getValue().handle();
        }


    }

}
