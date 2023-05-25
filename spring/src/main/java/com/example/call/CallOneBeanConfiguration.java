package com.example.call;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xuh
 * @date: 2023/5/25 09:24
 * @description:
 */
@Configuration(proxyBeanMethods = false)
public class CallOneBeanConfiguration {

    @Bean
    public CallOneBeanService1 callOneBeanService1(){
        return new CallOneBeanService1(callOneBeanDao());
    }

    @Bean
    public CallOneBeanService2 callOneBeanService2(){
        return new CallOneBeanService2(callOneBeanDao());
    }

    @Bean
    public CallOneBeanDao callOneBeanDao(){
        return new CallOneBeanDao();
    }
}
