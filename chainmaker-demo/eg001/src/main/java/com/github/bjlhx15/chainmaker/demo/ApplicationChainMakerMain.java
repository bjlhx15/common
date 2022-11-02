package com.github.bjlhx15.chainmaker.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//@SpringBootApplication
//@EnableConfigurationProperties
//public class ApplicationChainMakerMain extends SpringBootServletInitializer {
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(ApplicationChainMakerMain.class);
//    }
//    public static void main(String[] args) {
//        SpringApplication.run(ApplicationChainMakerMain.class, args);
//    }
//}

@SpringBootApplication
@EnableConfigurationProperties
public class ApplicationChainMakerMain {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationChainMakerMain.class, args);
    }
}
