package com.lunzi.spring.aop;

/**
 * @Author suosong
 * @Date 2018/9/22
 */
public interface PointCut {
    MethodMatcher getMethodMatcher();
    String getExpression();
}
