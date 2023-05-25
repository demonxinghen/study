package com.example.conditional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: xuh
 * @date: 2023/5/25 10:59
 * @description:
 */
public abstract class BaseCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String os = context.getEnvironment().getProperty("os.name");
        if (StringUtils.isBlank(os)){
            throw new RuntimeException("获取系统异常");
        }
        System.out.println("当前操作系统为：" + os);
        return match(os);
    }

    protected abstract boolean match(String os);
}
