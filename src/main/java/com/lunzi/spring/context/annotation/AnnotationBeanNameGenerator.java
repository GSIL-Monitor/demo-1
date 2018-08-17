package com.lunzi.spring.context.annotation;

import com.lunzi.spring.beans.factory.annotation.AnnotationBeanDefinition;
import com.lunzi.spring.beans.factory.config.BeanDefinition;
import com.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lunzi.spring.beans.factory.support.BeanNameGenerator;
import com.lunzi.spring.core.io.type.AnnotationMetadata;
import com.lunzi.spring.stereotypa.Component;
import com.lunzi.spring.utils.ClassUtils;

import java.beans.Introspector;

/**
 * 先取Component的value属性，如果取不到，就用骆驼式代替
 *
 * @Author suosong
 * @Date 2018/8/11
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

    @Override
    public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
        if (beanDefinition instanceof AnnotationBeanDefinition) {
            AnnotationBeanDefinition annotationBeanDefinition = (AnnotationBeanDefinition) beanDefinition;
            AnnotationMetadata metadata = annotationBeanDefinition.getAnnotationMetadata();
            Object beanName = metadata.getAnnotationAttributeValue(Component.class.getName(), "value");
            if (null == beanName || ((String)beanName).trim().equals("")) {//如果注解后面没有括号，那么value的值就是null 而不是默认值
                return buildBeanName(beanDefinition.getBeanClassName());
            }
            return (String) beanName;
        }
        throw new RuntimeException("找不到bean 的 name");
    }

    private String buildBeanName(String beanClassName) {
        String shortName = ClassUtils.getShortName(beanClassName);
        return Introspector.decapitalize(shortName);
    }
}
