package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.BeanFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class AnnotationBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    Object configObj;//自己私自加上的

    @Override
    public Object getBean(String name) {

        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        if (beanDefinition == null) return null;
        try {
            Method method = configObj.getClass().getMethod(beanDefinition.getMethodName());
            Object obj = method.invoke(configObj, null);
            return obj;
        } catch (Exception e) {
            throw new BeanCreationException("创建bean对象失败", e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }
    @Override
    public void setConfigObj(Object configObj) {
        this.configObj = configObj;
    }
}
