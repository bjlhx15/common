package com.github.bjlhx15.common.beanreg;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestServiceImpl implements ITestService {
    private String config;

    public TestServiceImpl(String config) {
        this.config = config;
    }

    @Override
    public void show() {
        log.error("config:"+config);
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
