package com.lhx.spring.services.impl;

import com.github.pagehelper.PageHelper;
import com.lhx.spring.entiry.auto.User;
import com.lhx.spring.mapper.UserMapperExt;
import com.lhx.spring.mapper.auto.UserMapper;
import com.lhx.spring.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserMapperExt userMapperExt;

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteUser(String id) {
        return 0;
    }

    @Override
    public List<User> queryList() {
        return userMapperExt.queryList();
    }
}
