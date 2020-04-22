package com.github.bjlhx15.springaop.testconditional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConditionalController {
    @Autowired
    ITestConditionalService testConditionalService;


    @GetMapping("/get")
    @ResponseBody
    public Object get(Long id) {
        String s = testConditionalService.get();
        return s;
    }
}
