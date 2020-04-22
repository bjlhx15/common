package com.github.bjlhx15.core.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 默认方式 也就是http方式 没有连接池 使用时开启 @Configuration
 */
//@Configuration
public class Config01Deafult {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
}
