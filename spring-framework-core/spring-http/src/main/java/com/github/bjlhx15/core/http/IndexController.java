package com.github.bjlhx15.core.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class IndexController {
    @RequestMapping(value = "test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "ok";
    }

    @RequestMapping(value = "test2",method = RequestMethod.GET)
    @ResponseBody
    public Object test2() {
        Map<String, String> map = new HashMap<>();
        map.put("code", "2000");
        return map;
    }
}
