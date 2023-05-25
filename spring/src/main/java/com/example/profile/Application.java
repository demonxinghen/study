package com.example.profile;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author: xuh
 * @date: 2023/5/25 14:11
 * @description:
 */
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String active = "dev";
        // 设置环境,决定要加载哪些bean
        environment.setActiveProfiles(active);
        // 此处即使注册了,环境不对的情况下,也不会注册
        applicationContext.register(DevConfiguration.class, ProcConfiguration.class);
        applicationContext.refresh();
        try {
            System.out.println(applicationContext.getBean(DevConfiguration.class));
        } catch (BeansException e) {
            System.out.println("当前环境为：" + active + ",不加载DevConfiguration");
        }
        try {
            System.out.println(applicationContext.getBean(ProcConfiguration.class));
        } catch (BeansException e) {
            System.out.println("当前环境为：" + active + ",不加载ProcConfiguration");
        }
    }
}
