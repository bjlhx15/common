package com.github.bjlhx15.chainmaker.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/get")
    @ResponseBody
    public String updateUser() {
        return "ok";
    }


}
