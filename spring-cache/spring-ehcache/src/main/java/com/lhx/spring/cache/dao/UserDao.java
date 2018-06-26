package com.lhx.spring.cache.dao;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.lhx.spring.cache.entity.User;

@Repository
public class UserDao {
	public User queryUser() {
		return new User("zhangsan", new Random().nextInt(50));
	}
}
