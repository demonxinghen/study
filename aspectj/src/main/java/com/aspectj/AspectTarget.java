package com.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/25 17:36
 * @description: 目标类
 */
@Component
public class AspectTarget {

    public void test(){
        System.out.println("aspectj test...");
    }
}
