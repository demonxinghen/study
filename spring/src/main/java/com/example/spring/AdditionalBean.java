package com.example.spring;

import jakarta.inject.Inject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Indexed;

/**
 * @author: xuh
 * @date: 2023/5/24 09:45
 * @description:
 */
@Indexed
public class AdditionalBean {

    @Inject
    private ApplicationContext applicationContext;
}
