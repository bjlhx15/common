package com.lhx.spring.cache.services;

import com.lhx.spring.cache.entity.User;

public interface IUserService {
	User queryUser(String id);
}
