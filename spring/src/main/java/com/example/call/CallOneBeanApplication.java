package com.example.call;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: xuh
 * @date: 2023/5/25 09:25
 * @description:
 */
public class CallOneBeanApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CallOneBeanConfiguration.class);
        CallOneBeanService1 callOneBeanService1 = applicationContext.getBean(CallOneBeanService1.class);
        CallOneBeanService2 callOneBeanService2 = applicationContext.getBean(CallOneBeanService2.class);
        System.out.println(callOneBeanService1.getCallOneBeanDao() == callOneBeanService2.getCallOneBeanDao());
    }
}
