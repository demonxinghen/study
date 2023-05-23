package com.example.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/22 10:36
 * @description:
 */
@Component
public class BeanB implements InitializingBean {

    public BeanB() {
        System.out.println("创建实例B");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("B被初始化了");
    }
}
