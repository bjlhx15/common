package com.github.bjlhx15.boot2cache.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SessionApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(SessionApplicationMain.class, args);
    }

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
}
