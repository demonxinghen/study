package com.aspectj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AspectjApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AspectjApplication.class, args);
        AspectTarget aspectTarget = applicationContext.getBean(AspectTarget.class);
        aspectTarget.test();
    }

}
