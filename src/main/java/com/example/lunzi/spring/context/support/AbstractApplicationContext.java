package com.example.lunzi.spring.context.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;

import com.example.lunzi.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.example.lunzi.spring.beans.factory.config.BeanPostProcessor;
import com.example.lunzi.spring.beans.factory.config.ConfigurableBeanFactory;
import com.example.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.example.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.lunzi.spring.context.ApplicationContext;
import com.example.lunzi.spring.core.io.Resource;
import com.example.lunzi.spring.utils.ClassUtils;

/**
 * @Author suosong
 * @Date 2018/7/12
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    DefaultBeanFactory factory;
    ClassLoader beanClassLoader;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = this.getResourceByPath(path);
        reader.loadBeanDefinitions(resource);
        //放入beanClassLoader,用于加载bean
        factory.setBeanClassLoader(this.getBeanClassLoader());
        //注册beanPostProcessor
        this.registerBeanPostProcessor(factory);

    }
    //org.springframework.context.support.AbstractApplicationContext

    /**
     * 数据源不一样，模板模式
     * @param
     * @return
     */
    protected abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String name) {
        return factory.getBean(name);
    }


    //接口继承有点不顺
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
    }

    /*//ConfigurableBeanFactory接口定义
    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }*/

    private void registerBeanPostProcessor(ConfigurableBeanFactory factory){
        BeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor(factory);
        factory.addBeanPostProcessor(beanPostProcessor);
    }
}
