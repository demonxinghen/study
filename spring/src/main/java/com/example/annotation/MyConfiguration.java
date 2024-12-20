package com.example.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @author: xuh
 * @date: 2023/5/24 06:09
 * @description:
 */
@Configuration("secondConfiguration")
//@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION))
@ComponentScan(useDefaultFilters = false)
public class MyConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }
}
