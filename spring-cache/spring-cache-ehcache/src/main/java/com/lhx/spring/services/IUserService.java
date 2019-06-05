package com.lhx.spring.services;

import com.lhx.spring.cache.entity.User;

public interface IUserService {
	User queryUser(String id);

	User updateUser(String id);

	int deleteUser(String id);
}
