package com.lhx.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableCaching
@ImportResource("classpath:/application-init.xml")
public class ApplicationMain {
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}
}
