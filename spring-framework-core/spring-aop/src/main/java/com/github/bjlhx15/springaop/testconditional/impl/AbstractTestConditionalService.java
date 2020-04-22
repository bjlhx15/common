package com.github.bjlhx15.springaop.testconditional.impl;

import com.github.bjlhx15.springaop.testconditional.ITestConditionalService;

public abstract class AbstractTestConditionalService implements ITestConditionalService {

    @Override
    public abstract String getBefore();

    @Override
    public String get() {
        String before = this.getBefore();
        // do something
        return "before:" + before;
    }
}
