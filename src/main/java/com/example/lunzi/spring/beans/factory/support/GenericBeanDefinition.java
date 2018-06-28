package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class GenericBeanDefinition implements BeanDefinition{
    private String name;
    private String className;
    private String methodName;

    public GenericBeanDefinition(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public GenericBeanDefinition(String name, String className, String methodName) {
        this.name = name;
        this.className = className;
        this.methodName = methodName;
    }


    @Override
    public String getClassName() {
        return this.className;
    }

    @Override
    public String getMethodName() {
        return this.methodName;
    }
}
