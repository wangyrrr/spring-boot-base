//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.session.data.redis.config.ConfigureRedisAction;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
////@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 7200)
//public class RedisSessionConfig {
//
//    @Bean
//    public ConfigureRedisAction initConfigureRedisAction() {
//        return ConfigureRedisAction.NO_OP;
//    }
//
//}