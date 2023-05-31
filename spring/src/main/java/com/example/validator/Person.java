package com.example.validator;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: xuh
 * @date: 2023/5/30 09:50
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
// @Validated(PersonValidator.class)
public class Person {

    private String name;

    @Max(15)
    private int age;

}
