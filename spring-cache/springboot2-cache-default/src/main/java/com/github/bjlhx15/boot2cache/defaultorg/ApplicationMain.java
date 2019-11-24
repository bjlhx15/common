package com.github.bjlhx15.boot2cache.defaultorg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationMain {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}
}
