package com.example.lunzi.spring.beans.factory.config;

import com.example.lunzi.spring.beans.BeansException;

/**
 * bean生命周期接口
 * @Author suosong
 * @Date 2018/8/13
 */
public interface BeanPostProcessor {
    void beforeInitialization(Object bean, String beanName) throws BeansException;
    void afterInitialization(Object bean,String beanName) throws BeansException;
}
