package com.github.bjlhx15.springaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MyTestService {
    Logger logger = LoggerFactory.getLogger(MyTestService.class);
    public String doSomething1(){
        logger.info("invoking doSomething1......");
        return "doSomething1";
    }

    public String doSomething2(){
        logger.info("invoking doSomething2......");
        return "doSomething2";
    }
}
