package com.lunzi.spring.beans.factory;


import org.springframework.beans.factory.NoSuchBeanDefinitionException;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public interface BeanFactory {

    Object getBean(String name);
    /**
     * 通过beanName 拿到 Class
     * @param beanName
     * @return
     */
    Class getType(String beanName) throws NoSuchBeanDefinitionException;
}