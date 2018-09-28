package com.lunzi.spring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author suosong
 * @Date 2018/9/23
 */
public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice {
    public AspectJAfterReturningAdvice(Method adviceMethod, AspectJExpressionPointCut pointcut, Object adviceObject){
        super(adviceMethod,pointcut,adviceObject);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object o = mi.proceed();
        //例如：调用TransactionManager的commit方法
        this.invokeAdviceMethod();
        return o;
    }
}
