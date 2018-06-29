package com.example.lunzi.spring.context.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.example.lunzi.spring.beans.factory.support.GenericBeanDefinition;
import com.example.lunzi.spring.context.BeanDefinitionReader;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

/**
 * 读取注解资料，单一职责原则
 * @Author suosong
 * @Date 2018/6/28
 */
public class AnnotationBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }
    @Override
    public void loadBeanDefinitions(Class<?> clazz) throws BeanDefinitionStoreException{
        Method[] methods = clazz.getMethods();
        if (methods == null) return;
        for (Method method : methods) {
            Bean bean = method.getAnnotation(Bean.class);
            if (bean == null) continue;
            String name = method.getName();
            String className = method.getReturnType().getName();
            String methodName = method.getName();
            GenericBeanDefinition definition = new GenericBeanDefinition(name, className,methodName);
            //注册
            this.registry.registerBeanDefinition(name, definition);
        }
    }


}
