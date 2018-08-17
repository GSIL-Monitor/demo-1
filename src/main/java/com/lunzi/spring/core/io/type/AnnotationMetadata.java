package com.lunzi.spring.core.io.type;

import java.util.Set;

public interface AnnotationMetadata {

    Set<String> getAnnotationTypes();
    boolean hasAnnotation(String annotationClassName);
    Object getAnnotationAttributeValue(String annotationValue ,String attributeName);


}
