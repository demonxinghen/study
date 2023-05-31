package com.example.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author: xuh
 * @date: 2023/5/30 09:51
 * @description:
 */
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Person p = (Person) target;
        if (p.getAge() < 0){
            errors.rejectValue("name", "negativeValue");
        } else if (p.getAge() > 110) {
            errors.rejectValue("name", "too.darn.old");
        }
    }
}
