package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.BeanFactory;
import com.example.lunzi.spring.context.BeanDefinitionReader;
import com.example.lunzi.spring.context.support.AnnotationBeanDefinitionReader;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class AnnotationBeanFactory implements BeanFactory ,BeanDefinitionRegistry{
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    Object o ;//自己的实例,自己加上的。打算用Method来获得bean(注解)，
    Class<?> configClazz;//配置文件的字节码对象，未来需要优化

    BeanDefinitionReader reader;

    public AnnotationBeanFactory(Class<?> classType)  {
        this.reader = new AnnotationBeanDefinitionReader(this);
        initBeanDefinitionMap(classType);
        //这个需要去看spring源码，这一块处理的不好
        configClazz = classType;
        try {
            o = classType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化definitionMap
     * 目前只支持方法名为name
     *
     * @param
     */
    private void initBeanDefinitionMap(Class<?> clazz)  {
        this.reader.loadBeanDefinitions(clazz);
    }

    @Override
    public Object getBean(String name) {

        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        if (beanDefinition == null) return null;
        String className = beanDefinition.getClassName();
        try {
            //return Class.forName(className).newInstance();
            Method method = configClazz.getMethod(beanDefinition.getMethodName());
            Object obj = method.invoke(this.o,null);
            return obj;
        }  catch (Exception e) {
            throw new BeanCreationException("创建bean对象失败", e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        this.beanDefinitionMap.put(beanName,beanDefinition);
    }
}
