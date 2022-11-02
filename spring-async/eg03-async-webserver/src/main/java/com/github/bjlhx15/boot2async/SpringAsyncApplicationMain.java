package com.github.bjlhx15.boot2async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringAsyncApplicationMain extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringAsyncApplicationMain.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAsyncApplicationMain.class, args);
    }

}

