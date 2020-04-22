package com.github.bjlhx15.core.basicauth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "主页控制层")
@RestController
@RequestMapping("/")
public class IndexController {

    @ApiOperation(value = "根据用户名获取用户对象", httpMethod = "GET", response = String.class,
            notes = "根据用户名获取用户对象1")
    @ResponseBody
    @RequestMapping(value = "/AuthTest", method = RequestMethod.GET)
    public String AuthTest(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        return "OK";
    }
}
