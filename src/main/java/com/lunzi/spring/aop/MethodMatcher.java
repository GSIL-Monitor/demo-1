package com.lunzi.spring.aop;

import java.lang.reflect.Method;

/**
 * 方法是否符合 aop切点表达式
 */
public interface MethodMatcher {
    boolean matches(Method method);
}
