package com.github.bjlhx15.boot2cache.defaultorg.services;


import com.github.bjlhx15.boot2cache.defaultorg.entity.User;

public interface IUserService {
	User queryUser(String id);

	User updateUser(String id);

	int deleteUser(String id);
}
