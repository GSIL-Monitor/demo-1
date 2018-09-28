package com.lunzi.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * Advice 一个动词，泛指代码增强的过程
 */
public interface Advice extends MethodInterceptor{
    PointCut getPointCut();
}
