package com.lunzi.spring.beans.factory.annotation;

import com.lunzi.spring.core.io.type.AnnotationMetadata;

/**
 * 此接口不是多此一举，生成bean name的时候，要用到
 * 因为不管是在xml 还是在注解中 bean的name都是可以不写的
 */
public interface AnnotationBeanDefinition {


    AnnotationMetadata getAnnotationMetadata();

}
