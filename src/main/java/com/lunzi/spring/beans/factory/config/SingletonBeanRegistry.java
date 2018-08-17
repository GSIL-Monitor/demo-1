package com.lunzi.spring.beans.factory.config;

/**
 * spring提供的，操作单例对象的接口
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName,Object obj);
    Object getSingleton(String beanName);
}
