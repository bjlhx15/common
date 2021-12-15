package com.github.bjlhx15.boot2cache.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cache")
@Slf4j

public class CacheController {
    @RequestMapping("login")
    @ResponseBody
    public String login(HttpServletRequest request) {
        request.getSession().setAttribute("accountId", "1");
        return "ok";
    }

    @RequestMapping("biz")
    @ResponseBody
    public String biz(HttpServletRequest request) {
        Object accountId = request.getSession().getAttribute("accountId");
        if ("1".equals(accountId)) {
            return "ok";
        }

        return "fail";
    }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("accountId");
        return "ok";
    }
}
