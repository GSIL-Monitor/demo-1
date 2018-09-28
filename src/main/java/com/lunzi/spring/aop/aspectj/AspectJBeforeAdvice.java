package com.lunzi.spring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @Author suosong
 * @Date 2018/9/23
 */
public class AspectJBeforeAdvice extends AbstractAspectJAdvice{
    public AspectJBeforeAdvice(Method adviceMethod, AspectJExpressionPointCut pointcut, Object adviceObject){
        super(adviceMethod,pointcut,adviceObject);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //例如： 调用TransactionManager的start方法
        this.invokeAdviceMethod();
        Object o = mi.proceed();
        return o;
    }

}
