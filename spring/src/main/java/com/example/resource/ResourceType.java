package com.example.resource;

import jakarta.annotation.Resource;

/**
 * @author: xuh
 * @date: 2023/5/23 17:58
 * @description:
 */
public class ResourceType {

    @Resource
    private Target targetResource;

    public void say(){
        targetResource.sayHello();
    }
}
