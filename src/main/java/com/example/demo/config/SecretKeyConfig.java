package com.example.demo.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yanhongwei
 * @Date: 2023-03-07  15:33
 */
@Configuration
public class SecretKeyConfig {

    @Value("${sys.auth.jwtKey}")
    public String jwtKey;

    @Bean
    public SecretKey key() {
        SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        return key;
    }
}
