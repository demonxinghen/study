package com.example.spring;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author: xuh
 * @date: 2023/5/23 09:47
 * @description:
 */
public class Autowiring implements InitializingBean {

    private Candidate candidate;

//    public Autowiring(Candidate candidate) {
//        this.candidate = candidate;
//    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-------------------");
        System.out.println(candidate);
        System.out.println("-------------------");
    }
}
