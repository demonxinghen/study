package com.example.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/22 10:36
 * @description:
 */
@Component
public class BeanA implements InitializingBean {

    public BeanA() {
        System.out.println("创建实例A");
    }

    private BeanB beanB;

    public void setBeanB(BeanB beanB){
        this.beanB = beanB;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("A被初始化了");
    }
}
