package com.github.bjlhx15.boot2cache.lettucepool.dao;

import com.github.bjlhx15.boot2cache.lettucepool.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Random;

@Repository
public class UserDao {
	public User queryUser() {
		return new User("zhangsan", new Random().nextInt(50),new Date());
	}
}
