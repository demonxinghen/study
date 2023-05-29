package com.aspectj;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;

/**
 * @author: xuh
 * @date: 2023/5/25 17:41
 * @description: 在VM options上添加-javaagent:/usr/local/repo/org/springframework/spring-instrument/6.0.9/spring-instrument-6.0.9.jar
 */
@EnableLoadTimeWeaving(aspectjWeaving = EnableLoadTimeWeaving.AspectJWeaving.ENABLED)
@Configuration
@ComponentScan(basePackages = "com.aspectj")
public class AspectConfig {

}
