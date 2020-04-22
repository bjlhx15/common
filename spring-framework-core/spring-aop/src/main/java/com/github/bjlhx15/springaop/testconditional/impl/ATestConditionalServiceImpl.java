package com.github.bjlhx15.springaop.testconditional.impl;

import com.github.bjlhx15.springaop.testconditional.ConstBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service("aTestConditionalServiceImpl")
@ConditionalOnProperty(name = "set.test",havingValue = ConstBean.atest,matchIfMissing = true)
public class ATestConditionalServiceImpl extends AbstractTestConditionalService {
    @Override
    public String getBefore() {
        return "atest test";
    }
}
