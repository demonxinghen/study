package com.example.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: xuh
 * @date: 2023/5/24 06:10
 * @description:
 */
@Component
@PropertySource("my.properties")
public class MyProperties {

    @Value("${application.author:lao liu}")
    private String author;

    public void sayGoodbye(){
        System.out.println(author + ": bye bye!");
    }
}
