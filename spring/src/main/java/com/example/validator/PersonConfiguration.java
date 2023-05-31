package com.example.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author: xuh
 * @date: 2023/5/30 11:39
 * @description:
 */
@Configuration
public class PersonConfiguration {

    @Bean
    public MethodValidationPostProcessor validationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
