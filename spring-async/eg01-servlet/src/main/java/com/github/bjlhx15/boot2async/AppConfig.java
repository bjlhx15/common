package com.github.bjlhx15.boot2async;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //同步拦截器
//        registry.addInterceptor(new HelloInterceptor()).addPathPatterns("/**");
        //异步拦截器
        registry.addInterceptor(new AsyncHelloInterceptor()).addPathPatterns("/**");
    }
}
