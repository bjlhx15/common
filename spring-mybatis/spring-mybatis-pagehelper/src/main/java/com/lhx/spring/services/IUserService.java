package com.lhx.spring.services;


import com.lhx.spring.entiry.auto.User;

import java.util.List;

public interface IUserService {
	User get(int id);
	int deleteUser(String id);
	List<User> queryList();
}
