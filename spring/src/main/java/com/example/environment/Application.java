package com.example.environment;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author: xuh
 * @date: 2023/5/25 14:58
 * @description: 在idea的VM options配置了-Dmy-property=xuhui, 在Environment variables里配置了my-property=xiaohong;your-property=xiaoming,从spring的environment获取属性
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class, CustomProperty.class);
        applicationContext.refresh();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String my_property = environment.getProperty("my-property");
        String your_property = environment.getProperty("your-property");
        String his_property = environment.getProperty("his-property");
        // 返回的是系统变量的值
        System.out.println("my-property：" + my_property);
        // 返回的是环境变量的值
        System.out.println("your-property：" + your_property);
        System.out.println("his-property：" + his_property);
    }
}
