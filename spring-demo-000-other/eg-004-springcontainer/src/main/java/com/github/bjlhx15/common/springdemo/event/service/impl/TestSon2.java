package com.github.bjlhx15.common.springdemo.event.service.impl;

import com.github.bjlhx15.common.springdemo.event.service.AbstractTestSon;
import org.springframework.stereotype.Service;

/**
 * @author lihongxu6
 * @version 1.0
 * @className TestSon1
 * @description TODO
 * @date 2021-01-12 10:56
 */
@Service
public class TestSon2 extends AbstractTestSon {
    @Override
    public void son() {
        System.out.println("TestSon2");
    }
}
