package com.github.bjlhx15.boot2cache.session.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

@Order(1)
@Configuration
@EnableScheduling
@EnableConfigurationProperties({SessionProperties.class})
public class HttpSessionConfiguration extends SpringHttpSessionConfiguration {
    @Autowired
    private SessionProperties sessionProperties;

    @Bean
    public AuthSessionRespository sessionRespository() {
        return new AuthSessionRespository(sessionProperties);
    }

    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
