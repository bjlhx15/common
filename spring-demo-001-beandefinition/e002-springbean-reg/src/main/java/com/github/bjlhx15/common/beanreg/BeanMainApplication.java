package com.github.bjlhx15.common.beanreg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BeanMainApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BeanMainApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BeanMainApplication.class, args);
        System.err.println("启动成功");
    }
}
