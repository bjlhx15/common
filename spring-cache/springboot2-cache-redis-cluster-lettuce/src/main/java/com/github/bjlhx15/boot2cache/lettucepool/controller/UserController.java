package com.github.bjlhx15.boot2cache.lettucepool.controller;

import com.github.bjlhx15.boot2cache.lettucepool.entity.User;
import com.github.bjlhx15.boot2cache.lettucepool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/user")
    @ResponseBody
    public User queryUser(String id) {
        return this.iUserService.queryUser(id);
    }

    @GetMapping("/user/u")
    @ResponseBody
    public String updateUser() {
        this.iUserService.updateUser("1");
        return "ok";
    }

    @GetMapping("/user/d")
    @ResponseBody
    public String deleteUser() {
        this.iUserService.deleteUser("1");
        return "ok";
    }

    @GetMapping("/user/d/all")
    @ResponseBody
    public String deleteAll() {
        this.iUserService.deleteAll("1");
        return "ok";
    }
}
