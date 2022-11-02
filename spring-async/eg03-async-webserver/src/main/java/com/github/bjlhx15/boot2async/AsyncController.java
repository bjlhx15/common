package com.github.bjlhx15.boot2async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/sync")
@Slf4j
public class AsyncController {
    @RequestMapping("timeout")
    @ResponseBody
    public String timeout(HttpServletRequest request) throws InterruptedException {
        System.out.println("request start:"+new Date());
        String timeout = request.getParameter("timeout");
        int time = Integer.parseInt(timeout);
        TimeUnit.MILLISECONDS.sleep(time);
        System.out.println("request end:"+new Date());
        return "web ok";
    }


}
