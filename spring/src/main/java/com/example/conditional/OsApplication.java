package com.example.conditional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xuh
 * @date: 2023/5/25 11:09
 * @description:
 */
public class OsApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(OsConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName: beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
