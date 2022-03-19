package com.example.springdatademo.repository;

import com.example.springdatademo.PostListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostRepositoryTestConfig {

    @Bean
    public PostListener postListener() {
        return new PostListener();
    }
}
