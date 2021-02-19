package com.github.bjlhx15.common.springdemo.springbootjunit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
//@EnableAsync
@ImportResource({
        "classpath:spring-config.xml"
})
//@PropertySource(value = {
//        "${config.path}"
//}, encoding = "utf-8")
@MapperScan("com.github.bjlhx15.common.springdemo.springbootjunit.repository")
public class DemoApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }


    //main启动
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(DemoApplication.class, args);
        System.err.println("\r\n---项目 启动成功---");
    }

}
