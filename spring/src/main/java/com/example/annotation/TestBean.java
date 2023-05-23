package com.example.annotation;

import jakarta.annotation.Resource;

/**
 * @author: xuh
 * @date: 2023/5/24 07:35
 * @description:
 */
public class TestBean {

    @Resource
    private ThirdProperties thirdProperties;

    public ThirdProperties getThirdProperties() {
        return thirdProperties;
    }
}
