package com.example.lunzi.spring.context;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;

/**
 * 读取配置文件
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(Class<?> clazz) throws BeanDefinitionStoreException;
}
