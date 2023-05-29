package com.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author: xuh
 * @date: 2023/5/25 17:37
 * @description:
 */
@Aspect
public class AspectInvoke {

    @Pointcut("execution(public * com.aspectj..*.*(..))")
    public void pointCut(){

    }

    @Around("pointCut()")
    public void advise(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Signature signature = proceedingJoinPoint.getSignature();
        // TODO 验证失败,有空再研究
        System.out.println(signature + " start...");
        proceedingJoinPoint.proceed();
        System.out.println(signature + " end...");
    }
}
