package com.lhx.spring.dao;

import java.util.Random;

import com.lhx.spring.entity.User;
import org.springframework.stereotype.Repository;

import com.lhx.spring.cache.entity.User;

@Repository
public class UserDao {
	public User queryUser() {
		return new User("zhangsan", new Random().nextInt(50));
	}
}
