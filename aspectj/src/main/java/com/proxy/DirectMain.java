package com.proxy;

/**
 * @author: xuh
 * @date: 2023/6/2 10:24
 * @description: 直接调用方式,非代理
 */
public class DirectMain {

    public static void main(String[] args) {
        Pojo pojo = new SimplePojo();
        // 此处pojo为直接实例,非代理
        pojo.foo();
    }
}
