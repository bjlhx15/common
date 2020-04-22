package com.github.bjlhx15.springaop.service;

import com.github.bjlhx15.springaop.ApplicationMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationMain.class)
public class MyTestServiceTest {
    @Autowired
    MyTestService myTestService;

    @Test
    public void doSomething1() {
        myTestService.doSomething1();
    }
}