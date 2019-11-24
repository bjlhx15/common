package com.github.bjlhx15.springcache.boot2.redis.pool.services;


import com.github.bjlhx15.springcache.boot2.redis.pool.entity.User;

public interface IUserService {
	User queryUser(String id);

	User updateUser(String id);

	int deleteUser(String id);
}
