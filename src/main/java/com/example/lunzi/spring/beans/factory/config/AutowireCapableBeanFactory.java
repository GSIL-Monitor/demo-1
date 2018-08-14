package com.example.lunzi.spring.beans.factory.config;

import com.example.lunzi.spring.beans.factory.BeanFactory;

/**
 * 此接口的唯一目的就是解析Autowire注解
 */
public interface AutowireCapableBeanFactory extends BeanFactory{
    Object resolveDependency(DependencyDescriptor dependencyDescriptor) throws ClassNotFoundException;
}
