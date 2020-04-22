package com.github.bjlhx15.boot2cache.lettucepool.services;


import com.github.bjlhx15.boot2cache.lettucepool.entity.User;

public interface IUserService {
	User queryUser(String id);

	User updateUser(String id);

	int deleteUser(String id);

	int deleteAll(String id);
}
