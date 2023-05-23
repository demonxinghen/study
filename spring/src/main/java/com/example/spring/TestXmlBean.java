package com.example.spring;

/**
 * @author: xuh
 * @date: 2023/5/22 11:45
 * @description:
 */
public class TestXmlBean {

    private String targetBean;

    public void setTargetBean(String targetBean) {
        this.targetBean = targetBean;
    }

    public void destroy() throws Exception {
        System.out.println("---------");
        System.out.println(targetBean);
        System.out.println("---------");
    }
}
