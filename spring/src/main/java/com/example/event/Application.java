package com.example.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: xuh
 * @date: 2023/5/29 10:30
 * @description:
 */
@ComponentScan(basePackages = "com.example.event")
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        SendEmailService emailService = applicationContext.getBean(SendEmailService.class);
        emailService.sendEmail("john.doe@example.org", "i like you, i am john.doe");
        emailService.sendEmail("hello@example.org", "i like you, i am hello");

    }
}
