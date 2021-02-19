package com.github.bjlhx15.common.springdemo.springbootjunit.controller;

import com.alibaba.fastjson.JSON;
import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import com.github.bjlhx15.common.springdemo.springbootjunit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserController
 * @description TODO
 * @date 2021-01-07 09:26
 */
@RestController
@RequestMapping("/user/postform")
public class UserPostFormController {
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/insert")
    public ResponseEntity insert(User record) {
        System.out.println(JSON.toJSONString(record));
        return ResponseEntity.ok(userService.insertUser(record));
    }
}
