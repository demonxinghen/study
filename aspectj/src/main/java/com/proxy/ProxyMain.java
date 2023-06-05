package com.proxy;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author: xuh
 * @date: 2023/6/2 10:26
 * @description: 代理调用
 */
public class ProxyMain {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new SimplePojo());
        proxyFactory.addInterface(Pojo.class);
        // proxyFactory.addAdvice();

        Pojo pojo = (Pojo) proxyFactory.getProxy();
        System.out.println(pojo);
        // 此处的pojo是代理对象
        pojo.foo();
    }
}
