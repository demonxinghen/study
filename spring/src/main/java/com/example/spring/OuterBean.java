package com.example.spring;

/**
 * @author: xuh
 * @date: 2023/5/22 16:07
 * @description:
 */
public class OuterBean {

    private InnerBean innerBean;

    public void setInnerBean(InnerBean innerBean){
        this.innerBean = innerBean;
    }

    public InnerBean getInnerBean(){
        return innerBean;
    }
}

class InnerBean{

    private final String sex;

    private final Integer age;

    public InnerBean(String sex, Integer age) {
        this.sex = sex;
        this.age = age;
    }
}