package com.example.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/24 07:35
 * @description:
 */
@Component("secondComponent")
public class MyComponent {

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }
}
