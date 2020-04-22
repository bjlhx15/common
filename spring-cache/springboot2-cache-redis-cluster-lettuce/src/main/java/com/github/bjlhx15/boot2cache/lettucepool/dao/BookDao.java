package com.github.bjlhx15.boot2cache.lettucepool.dao;

import com.github.bjlhx15.boot2cache.lettucepool.entity.Book;
import com.github.bjlhx15.boot2cache.lettucepool.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Random;

@Repository
public class BookDao {
	public Book query() {
		return new Book("zhangsan", new Random().nextInt(50),new Date());
	}
}
