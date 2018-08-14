package com.example.lunzi.spring.beans.factory.config;

import com.example.lunzi.spring.beans.BeansException;

public interface InitializationAwareBeanPostProcessor extends BeanPostProcessor {

    Object beforeInitiallization(Class beanClass,String beanName) throws BeansException;
    boolean afterInitialization(Class beanClass,String beanName) throws BeansException;
    void postProcessPropertyValues(Object bean,String beanName) throws  BeansException;

}
