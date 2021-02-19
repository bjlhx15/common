package com.github.bjlhx15.common.springdemo.event.repository.auto.impl;


import com.github.bjlhx15.common.springdemo.event.domain.auto.User;
import com.github.bjlhx15.common.springdemo.event.repository.auto.UserMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lihongxu6
 * @version 1.0
 * @className UserMapperImpl
 * @description TODO
 * @date 2021-01-09 09:03
 */
@Repository
public class UserMapperImpl implements UserMapper {
    @Override
    public int insert(User record) {
        return 0;
    }
}
