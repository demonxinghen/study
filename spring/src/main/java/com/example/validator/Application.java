package com.example.validator;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: xuh
 * @date: 2023/5/30 09:51
 * @description: //TODO 当前校验未生效
 */
@ComponentScan("com.example.validator")
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
        PersonService personService = applicationContext.getBean(PersonService.class);
        personService.save(new Person("xuhui", 119));
    }
}
