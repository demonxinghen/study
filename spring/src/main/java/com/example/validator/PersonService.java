package com.example.validator;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author: xuh
 * @date: 2023/5/30 11:36
 * @description:
 */
@Service
@Validated
public class PersonService {

    @Resource
    private PersonValidator personValidator;

    

    public void save(@Valid Person person){
        System.out.println("保存成功：" + person);
    }
}
