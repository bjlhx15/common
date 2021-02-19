package com.github.bjlhx15.common.springdemo.springbootjunit.service.impl;

import com.github.bjlhx15.common.springdemo.springbootjunit.domain.auto.User;
import com.github.bjlhx15.common.springdemo.springbootjunit.repository.auto.UserMapper;
import com.github.bjlhx15.common.springdemo.springbootjunit.service.IDepartService;
import com.github.bjlhx15.common.springdemo.springbootjunit.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserServiceImpl
 * @description TODO
 * @date 2021-01-07 09:31
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper mapper;


    @Autowired(required = false)
    private IDepartService departService;

    @Override
    public int insertUser(User record) {
        System.out.println("1111");
        int insert = mapper.insert(record);
        System.out.println(insert);
        return insert;
    }

    @Override
    public List<User> queryList(User record) {
        List<User> result = new ArrayList<>();
        result.add(record);
        return result;
    }

    @Override
    public int insertUserAndDepart(User record) {
        int insert1 = departService.insert(record); //想mock
        int insert = mapper.insert(record); // 真实执行
        return insert1+insert;
    }


}
