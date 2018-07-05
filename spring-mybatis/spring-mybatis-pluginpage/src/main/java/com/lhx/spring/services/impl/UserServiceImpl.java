package com.lhx.spring.services.impl;

import com.lhx.spring.services.IUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements IUserService {


	@Override
	public int deleteUser(String id) {
		return 0;
	}
}
