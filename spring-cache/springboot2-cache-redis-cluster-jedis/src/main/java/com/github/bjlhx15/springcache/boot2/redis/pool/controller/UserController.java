package com.github.bjlhx15.springcache.boot2.redis.pool.controller;

import com.github.bjlhx15.springcache.boot2.redis.pool.entity.User;
import com.github.bjlhx15.springcache.boot2.redis.pool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/user")
    @ResponseBody
    public User queryUser() {
        return this.iUserService.queryUser("1");
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;//操作key-value都是字符串

    @Autowired
    private RedisTemplate redisTemplate;//操作key-value都是对象

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        stringRedisTemplate.opsForValue().set("a","sss");
        String a = stringRedisTemplate.opsForValue().get("a");
        return a;
    }
}
