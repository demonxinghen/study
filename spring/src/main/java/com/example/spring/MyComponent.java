package com.example.spring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/11 11:36
 * @description:
 */
public class MyComponent implements InitializingBean, DisposableBean {

    public MyComponent() {
        System.out.println("无参构造");
    }

    private String name = "Hellooo";

    {
        System.out.println(name + "World");
    }

    public void hello(){
        System.out.println("hello world");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean");
    }

    @PostConstruct
    private void post(){
        System.out.println("PostConstruct");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean");
    }

    private void des() {
        System.out.println("destroyMethod");
    }


    private void init() {
        System.out.println("initMethod");
    }

    @PreDestroy
    private void preDestroy(){
        System.out.println("preDestroy");
    }

    @Component
    static class ComponentB{

    }

}

@Component
class ComponentC{

}