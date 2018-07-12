package com.example.lunzi.spring.beans.factory.config;

import com.example.lunzi.spring.beans.factory.BeanFactory;

/**
 * beanFactory中一些可以配置的信息。  不方便在BeanFactory中暴露
 */
public interface ConfigurableBeanFactory extends BeanFactory {

    ClassLoader getBeanClassLoader();
    void setBeanClassLoader(ClassLoader beanClassLoader);
}
