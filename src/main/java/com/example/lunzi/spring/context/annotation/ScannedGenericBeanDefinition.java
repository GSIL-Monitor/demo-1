package com.example.lunzi.spring.context.annotation;

import com.example.lunzi.spring.beans.factory.annotation.AnnotationBeanDefinition;
import com.example.lunzi.spring.beans.factory.support.GenericBeanDefinition;
import com.example.lunzi.spring.core.io.type.AnnotationMetadata;

/**
 * @Author suosong
 * @Date 2018/8/11
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotationBeanDefinition{

    AnnotationMetadata annotationMetadata;

    public ScannedGenericBeanDefinition(String name, String beanClassName,AnnotationMetadata annotationMetadata) {
        super(name, beanClassName);
        this.annotationMetadata = annotationMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
