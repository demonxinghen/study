package com.example.environment;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/25 16:58
 * @description:
 */
@Component
@PropertySource("classpath:custom.properties")
public class CustomProperty {
}
