package com.example.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xuh
 * @date: 2023/5/25 11:07
 * @description:
 */
@Configuration
public class OsConfig {

    @Bean
    @Conditional(LinuxCondition.class)
    public Linux linux(){
        return new Linux();
    }

    @Bean
    @Conditional(MacCondition.class)
    public Mac mac(){
        return new Mac();
    }

    @Bean
    @Conditional(WindowsCondition.class)
    public Windows windows(){
        return new Windows();
    }
}
