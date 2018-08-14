package com.example.lunzi.spring.beans.factory.annotation;

import com.example.lunzi.spring.beans.BeansException;
import com.example.lunzi.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.example.lunzi.spring.beans.factory.config.InitializationAwareBeanPostProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理@Autowire 注解
 *
 * @Author suosong
 * @Date 2018/8/13
 */
public class AutowiredAnnotationProcessor implements InitializationAwareBeanPostProcessor {

    Class beanClass;
    AutowireCapableBeanFactory factory;

    public AutowiredAnnotationProcessor(Class beanClass, AutowireCapableBeanFactory factory) {
        this.beanClass = beanClass;
        this.factory = factory;
    }

    private InjectionMetaData buildAutowiredMetadata() {
        List<InjectionElement> injectionElements = new ArrayList<>();
        Field[] fields = this.beanClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation autoWiredAnnotation = field.getAnnotation(AutoWired.class);
            if (autoWiredAnnotation == null) continue;
            String requiredStr = ((AutoWired) autoWiredAnnotation).required();
            boolean required = "true".equals(requiredStr);
            InjectionElement ele = new AutowireFieldElement(required, field, factory);
            injectionElements.add(ele);
        }
        return new InjectionMetaData(beanClass, injectionElements);
    }


    @Override
    public Object beforeInitiallization(Class beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean afterInitialization(Class beanClass, String beanName) throws BeansException {
        return false;
    }

    @Override
    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        InjectionMetaData injectionMetaData = this.buildAutowiredMetadata();
        injectionMetaData.inject(bean);
    }

    @Override
    public void beforeInitialization(Object bean, String beanName) throws BeansException {

    }

    @Override
    public void afterInitialization(Object bean, String beanName) throws BeansException {

    }
}
