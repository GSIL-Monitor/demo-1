package com.example.lunzi.spring.core.io.type.classreading;

import com.example.lunzi.spring.core.io.Resource;

public interface MetadataReader {

    ClassMetadata getClassMetadata();
    AnnotationMetadata getAnnotationMetadata();
    Resource getResource();

}
