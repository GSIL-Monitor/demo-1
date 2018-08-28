package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Repository;

@Aspect
@Repository
public class AopTest {

   /* @Pointcut("execution(com.example.youyuan.dto.ResultJson com..*.*(..))")
    private void showMethod() {

    }

    @Around("showMethod()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("拦截到了======");
        return pjp.proceed();
    }*/
}
