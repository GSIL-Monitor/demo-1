package com.example.lunzi.spring.beans.factory.config;

import java.util.List;

/**
 * beanFactory中一些可以配置的信息。  不方便在BeanFactory中暴露
 */
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {

    ClassLoader getBeanClassLoader();
    void setBeanClassLoader(ClassLoader beanClassLoader);
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();
}
