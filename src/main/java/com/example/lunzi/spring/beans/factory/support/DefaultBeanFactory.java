package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class DefaultBeanFactory implements BeanFactory {
    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    Object o ;//自己的实例,自己加上的。打算用Method来获得bean(注解)，
    Class<?> configClazz;//配置文件的字节码对象，未来需要优化

    public DefaultBeanFactory(Class<?> classType)  {
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
    private void initBeanDefinitionMap(Class<?> configClass)  {

        Method[] methods = configClass.getMethods();
        if (methods == null) return;
        for (Method method : methods) {
            Bean bean = method.getAnnotation(Bean.class);
            if (bean == null) continue;
            String name = method.getName();
            String className = method.getReturnType().getName();
            String methodName = method.getName();
            GenericBeanDefinition definition = new GenericBeanDefinition(name, className,methodName);
            this.beanDefinitionMap.put(name, definition);
        }
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
        }  catch (IllegalAccessException e) {
            throw new BeanCreationException("创建bean对象失败", e);
        }  catch (NoSuchMethodException e) {
            throw new BeanCreationException("创建bean对象失败", e);
        } catch (InvocationTargetException e) {
            throw new BeanCreationException("创建bean对象失败", e);
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }
}
