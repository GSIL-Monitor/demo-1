package com.lunzi.spring.beans.factory.support;

import com.lunzi.spring.beans.factory.BeanFactory;
import com.lunzi.spring.beans.factory.config.RuntimeBeanReference;
import com.lunzi.spring.beans.factory.config.TypeStringValue;

/**
 * @Author suosong
 * @Date 2018/7/26
 */
public class BeanDefinitionValueResolver {
    private BeanFactory factory;

    public BeanDefinitionValueResolver(BeanFactory factory) {
        this.factory = factory;
    }

    /**
     *
     * @param value
     * @return
     */
    public Object resolveValueIfNecessary(Object value){
        if(value instanceof RuntimeBeanReference){
            RuntimeBeanReference runtimeBeanReference = (RuntimeBeanReference)value;
            String beanName = runtimeBeanReference.getBeanName();
            Object bean = this.factory.getBean(beanName);

            return bean;
        }else if(value instanceof TypeStringValue){
            TypeStringValue typeStringValue = (TypeStringValue)value;
            return typeStringValue.getValue();
        }
        throw new RuntimeException("parameter type is error");
    }
}
