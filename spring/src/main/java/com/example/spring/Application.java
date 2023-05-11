package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//        MyComponent myComponent = context.getBean(MyComponent.class);
        MyComponent myComponent = (MyComponent) context.getBean("name2");
        myComponent.hello();
        String[] names = context.getBeanNamesForType(MyComponent.class);
        for (String name : names) {
            System.out.println("Bean名称：" + name);
        }

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("--------");
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        System.out.println("--------");
        context.close();
    }

}
