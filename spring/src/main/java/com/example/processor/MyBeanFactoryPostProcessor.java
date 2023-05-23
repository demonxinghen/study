package com.example.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author: xuh
 * @date: 2023/5/23 14:31
 * @description:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition oracle = beanFactory.getBeanDefinition("oracle");
        oracle.setAutowireCandidate(true);

        BeanDefinition mysql = beanFactory.getBeanDefinition("mysql");
        mysql.setAutowireCandidate(false);
    }
}
