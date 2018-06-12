package com.lhx.spring.cache.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @SpringBootApplication(classes = ApplicationMain.class)
class RedisCacheConfigTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void stringTest() {
		ValueOperations<String, Object> valueOperations = this.redisTemplate.opsForValue();
		valueOperations.set("hello", "redis");
		System.out.println("useRedisDao = " + valueOperations.get("hello"));
	}

}
