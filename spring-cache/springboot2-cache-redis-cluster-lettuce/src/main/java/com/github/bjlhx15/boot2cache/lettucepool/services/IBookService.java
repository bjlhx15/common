package com.github.bjlhx15.boot2cache.lettucepool.services;


import com.github.bjlhx15.boot2cache.lettucepool.entity.Book;

public interface IBookService {
	Book query(String id);

	Book update(String id);

	int delete(String id);

	int deleteAll(String id);
}
