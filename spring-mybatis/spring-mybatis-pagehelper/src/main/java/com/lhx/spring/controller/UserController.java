package com.lhx.spring.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhx.spring.common.ResponseResult;
import com.lhx.spring.entiry.auto.User;
import com.lhx.spring.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/get")
    public User get() {
        return iUserService.get(1);
    }

    @GetMapping("/list")
    public ResponseResult list() {
        //配置分页
        PageHelper.startPage(1, 5);
        //针对第一个查询有效,其实如果不考虑前端分页就可以了
        List<User> list = iUserService.queryList();
        //将数据装载进PageInfo中，page即为结果
        PageInfo<User> page = new PageInfo<>(list);
        //设置pagination方便前端框架
        Map<String, Object> map = new HashMap<>();
        map.put("pagination", page);
        return new ResponseResult().setData(map);
    }
}
