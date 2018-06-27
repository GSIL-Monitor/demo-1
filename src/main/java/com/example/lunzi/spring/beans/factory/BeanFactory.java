package com.example.lunzi.spring.beans.factory;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public interface BeanFactory {
    Object getBean(String name);

    BeanDefinition getBeanDefinition(String person);
}
