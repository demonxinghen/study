package com.example.context.annotation;

import com.example.context.annotation.components.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xuh
 * @date: 2023/5/24 11:22
 * @description:
 */
public class AnnotationApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AnnotationApplication.class, Person.class);
        System.out.println(context.getBean(Person.class));
        context.registerShutdownHook();
    }
}
