package com.github.bjlhx15.common.springdemo.event.service;


import com.github.bjlhx15.common.springdemo.event.domain.auto.User;

import java.util.List;

/**
 * @author lihongxu6
 * @version 1.0
 * @className IUserService
 * @description TODO
 * @date 2021-01-07 09:27
 */
public interface IUserService {
    int insertUser(User record);

    List<User> queryList(User record);


    int insertUserAndDepart(User record);
}
