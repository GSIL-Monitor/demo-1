package com.lunzi.spring.aop.aspectj;

import com.lunzi.spring.aop.Advice;
import com.lunzi.spring.aop.PointCut;

import java.lang.reflect.Method;

/**
 *
 * @Author suosong
 * @Date 2018/9/23
 */
public abstract class AbstractAspectJAdvice implements Advice{
    /**
     * 要被插入的方法
     */
    protected Method adviceMethod;
    /**
     * aop表达式切点，查看一个方法是不是要被增强
     */
    protected AspectJExpressionPointCut pointcut;
    /**
     * 切面对象，要通过反射执行方法，首先得有对象
     */
    protected Object adviceObject;



    public AbstractAspectJAdvice(Method adviceMethod,
                                 AspectJExpressionPointCut pointcut,
                                 Object adviceObject){

        this.adviceMethod = adviceMethod;
        this.pointcut = pointcut;
        this.adviceObject = adviceObject;
    }

    /**
     * 为了简单起见，只支持无参方法
     * @throws Throwable
     */
    public void invokeAdviceMethod() throws  Throwable{

        adviceMethod.invoke(adviceObject);
    }
    @Override
    public PointCut getPointCut(){
        return this.pointcut;
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }
}
