package com.example.profile;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.*;

/**
 * @author: xuh
 * @date: 2023/5/25 14:12
 * @description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Profile("dev")
public @interface Dev {
}
