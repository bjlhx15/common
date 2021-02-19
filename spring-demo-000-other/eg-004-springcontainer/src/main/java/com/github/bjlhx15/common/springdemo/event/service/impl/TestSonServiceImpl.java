package com.github.bjlhx15.common.springdemo.event.service.impl;

import com.github.bjlhx15.common.springdemo.event.service.ITestSonService;
import org.springframework.stereotype.Service;

/**
 * @author lihongxu6
 * @version 1.0
 * @className TestSonServiceImpl
 * @description TODO
 * @date 2021-01-12 10:55
 */
@Service
public class TestSonServiceImpl implements ITestSonService {
    @Override
    public void get() {
        System.out.println("TestSonServiceImpl");
    }
}
