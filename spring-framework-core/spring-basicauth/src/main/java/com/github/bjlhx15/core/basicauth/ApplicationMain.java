package com.github.bjlhx15.core.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//xml资源
@ImportResource({
        "classpath:applicationContext.xml"
//        ,
//        "classpath:mvc-servlet.xml"
})
public class ApplicationMain extends SpringBootServletInitializer {
    //SpringBootServletInitializer web启动
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationMain.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
