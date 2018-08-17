package com.lunzi.spring.context.annotation;

import com.lunzi.spring.beans.factory.annotation.AnnotationBeanDefinition;
import com.lunzi.spring.beans.factory.support.GenericBeanDefinition;
import com.lunzi.spring.core.io.type.AnnotationMetadata;

/**
 * @Author suosong
 * @Date 2018/8/11
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotationBeanDefinition{

    AnnotationMetadata annotationMetadata;



    public ScannedGenericBeanDefinition(AnnotationMetadata annotationMetadata) {
        this.annotationMetadata = annotationMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
