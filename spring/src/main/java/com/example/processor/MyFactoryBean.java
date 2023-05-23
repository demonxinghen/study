package com.example.processor;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/23 15:10
 * @description: 这个bean被spring管理后,如果getBean("myFactoryBean")返回的是getObject()的对象,getBean("&myFactoryBean")才是返回这个FactoryBean
 */
@Component("myFactoryBean")
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
