package com.example.lunzi.spring.context.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.support.AnnotationBeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.support.AnnotationBeanFactory;
import com.example.lunzi.spring.context.ApplicationContext;
import org.springframework.core.io.Resource;

/**
 * @Author suosong
 * @Date 2018/7/11
 */
public class AnnotationConfigApplicationContext implements ApplicationContext{

    AnnotationBeanFactory factory;

    public AnnotationConfigApplicationContext(Class<?> configClass) {
        factory = new AnnotationBeanFactory();
        BeanDefinitionReader reader = new AnnotationBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(configClass);
    }

    @Override
    public Object getBean(String name) {
        return factory.getBean(name);
    }
    //Resource
}
