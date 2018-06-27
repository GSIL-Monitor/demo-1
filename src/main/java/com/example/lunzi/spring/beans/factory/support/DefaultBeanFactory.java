package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class DefaultBeanFactory implements BeanFactory {
    Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    public DefaultBeanFactory(Class<?> classType) throws InstantiationException, IllegalAccessException {
        load(classType);
    }

    /**
     * 初始化definitionMap
     * 目前只支持方法名为name
     * @param
     */
    private void load(Class<?> configClass) throws IllegalAccessException, InstantiationException {

        Method[] methods = configClass.getMethods();
        if(methods == null)return;
        for(Method method : methods){
            Bean bean = method.getAnnotation(Bean.class);
            if(bean == null)continue;
            String name = method.getName();
            String className = method.getReturnType().getName();
            GernericBeanDefinition definition = new GernericBeanDefinition(name,className);
            this.beanDefinitionMap.put(name,definition);
        }
    }

    @Override
    public Object getBean(String name) {
        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        if(beanDefinition == null) return null;
        String className = beanDefinition.getClassName();
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }
}
