package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class GernericBeanDefinition implements BeanDefinition{
    private String Name;
    private String className;

    public GernericBeanDefinition(String name, String className) {
        Name = name;
        this.className = className;
    }

    @Override
    public String getClassName() {
        return this.className;
    }
}
