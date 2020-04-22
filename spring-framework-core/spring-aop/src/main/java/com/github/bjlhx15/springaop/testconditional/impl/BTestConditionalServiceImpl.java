package com.github.bjlhx15.springaop.testconditional.impl;

import com.github.bjlhx15.springaop.testconditional.ConstBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service("bTestConditionalServiceImpl")
@ConditionalOnProperty(name = "set.test",havingValue = ConstBean.btest)
public class BTestConditionalServiceImpl extends AbstractTestConditionalService {
    @Override
    public String getBefore() {
        return "btest test";
    }
}
