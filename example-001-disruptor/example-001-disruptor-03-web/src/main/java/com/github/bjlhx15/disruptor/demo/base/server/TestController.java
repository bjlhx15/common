package com.github.bjlhx15.disruptor.demo.base.server;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {

    @RequestMapping("/test")
    public Map test(Integer q) throws InterruptedException {
        Common.start = Common.start == 0 ? System.currentTimeMillis() : Common.start;
        ++Common.count;
        System.out.println("count1:"+Common.count);
        Map<String, String> result = new HashMap<>();
        if (q % 2 == 0) {
            TimeUnit.SECONDS.sleep(5);
            result.put("id", q.toString());
            result.put("message", "==5000");
            result.put("name", "==5000");
        } else {
            TimeUnit.SECONDS.sleep(2);
            result.put("id", q.toString());
            result.put("message", "==2000");
            result.put("name", "==2000");
        }
        System.out.println(new Date()+"count2:"+Common.count);
//        if (q == 998) {
            Common.end = System.currentTimeMillis() - Common.start;
            System.out.println("end:"+Common.end);
//        }
        return result;
    }
}
