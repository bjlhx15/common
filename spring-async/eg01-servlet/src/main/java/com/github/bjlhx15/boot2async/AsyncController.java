package com.github.bjlhx15.boot2async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {
    @RequestMapping("servlet")
    @ResponseBody
    public String login(HttpServletRequest request) {
        return "ok";
    }


}
