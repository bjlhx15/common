package com.github.bjlhx15.common.beanreg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bean")
@Slf4j
public class TestController {
    @RequestMapping("reg")
    @ResponseBody
    public String reg() {
        ITestService testServiceA = new TestServiceImpl("AAAA");
        ApplicationContextUtil.registerSingletonBean("testA", testServiceA);
        ITestService testServiceB = new TestServiceImpl("BBBB");
        ApplicationContextUtil.registerSingletonBean("testB", testServiceB);
        return "ok";
    }
    @RequestMapping("reg2")
    @ResponseBody
    public String reg2() {
        ApplicationContextUtil.registerBean(ITestService.class,"testC");
        return "ok";
    }


    @RequestMapping("get")
    @ResponseBody
    public String get() {
        ITestService testA = ApplicationContextUtil.getBean("testA");
        testA.show();
        ITestService testB = ApplicationContextUtil.getBean("testB");
        testB.show();
        ITestService testC = ApplicationContextUtil.getBean("testC");
        testC.show();
        return "ok";
    }
}
