package com.example.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xuh
 * @date: 2023/5/11 14:05
 * @description:
 */
@Configuration
public class MyConfiguration {

    @Bean(initMethod = "init", destroyMethod = "des")
    public MyComponent create(){
        return new MyComponent();
    }
}
