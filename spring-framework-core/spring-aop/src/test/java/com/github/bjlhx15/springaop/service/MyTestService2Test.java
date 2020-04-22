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
public class MyTestService2Test {

    @Autowired
    MyTestService2 myTestService2;
    @Test
    public void test() throws IOException {
        myTestService2.sayHello();
        myTestService2.count();
    }
}