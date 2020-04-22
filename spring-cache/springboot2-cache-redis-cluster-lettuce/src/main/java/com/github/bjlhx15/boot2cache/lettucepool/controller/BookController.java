package com.github.bjlhx15.boot2cache.lettucepool.controller;

import com.github.bjlhx15.boot2cache.lettucepool.entity.Book;
import com.github.bjlhx15.boot2cache.lettucepool.entity.User;
import com.github.bjlhx15.boot2cache.lettucepool.services.IBookService;
import com.github.bjlhx15.boot2cache.lettucepool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private IBookService service;

    @GetMapping("/book")
    @ResponseBody
    public Book queryUser(String id) {
        return this.service.query(id);
    }

    @GetMapping("/book/u")
    @ResponseBody
    public String updateUser() {
        this.service.update("1");
        return "ok";
    }

    @GetMapping("/book/d")
    @ResponseBody
    public String deleteUser() {
        this.service.delete("1");
        return "ok";
    }

    @GetMapping("/book/d/all")
    @ResponseBody
    public String deleteAll() {
        this.service.deleteAll("1");
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
