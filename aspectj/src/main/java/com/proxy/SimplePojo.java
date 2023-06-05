package com.proxy;

/**
 * @author: xuh
 * @date: 2023/6/2 10:23
 * @description:
 */
public class SimplePojo implements Pojo{

    @Override
    public void foo() {
        // 不管是代理调用还是直接调用,此处的this都是直接实例,非代理
        this.bar();
        System.out.println(this);
    }


    public void bar(){}
}
