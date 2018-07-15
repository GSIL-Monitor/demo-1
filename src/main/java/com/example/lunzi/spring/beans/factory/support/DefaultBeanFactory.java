package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.config.ConfigurableBeanFactory;
import com.example.lunzi.spring.beans.factory.config.DefaultSingletonBeanRegistry;
import com.example.lunzi.spring.utils.ClassUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry  {

    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    ClassLoader beanClassLoader;//Bean加载器

    //BeanFactory接口定义
    @Override
    public Object getBean(String name) {

        BeanDefinition beanDefinition = this.getBeanDefinition(name);
        if (beanDefinition == null) return null;
        try {
            Object bean = null;
            String beanClassName = beanDefinition.getBeanClassName();
            //如果是单例的，从单例接口中拿
            if(beanDefinition.isSingleton()){
                bean = this.getSingleton(beanClassName);
                if(bean == null){
                    bean = createBean(beanDefinition);
                    this.registerSingleton(beanClassName,bean);
                }
            }else {
                bean = createBean(beanDefinition);
            }
            return bean;
        } catch (Exception e) {
            throw new BeanCreationException("创建bean对象失败", e);
        }
    }

    //BeanDefinitionRegistry接口定义
    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    //BeanDefinitionRegistry接口定义
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    //ConfigurableBeanFactory接口定义
    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
    }

    //ConfigurableBeanFactory接口定义
    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    private Object createBean(BeanDefinition beanDefinition) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return this.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName()).newInstance();
    }
}
