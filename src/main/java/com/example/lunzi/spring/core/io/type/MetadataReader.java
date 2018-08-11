package com.example.lunzi.spring.core.io.type;

import com.example.lunzi.spring.core.io.Resource;

public interface MetadataReader {

    ClassMetadata getClassMetadata();
    AnnotationMetadata getAnnotationMetadata();
    Resource getResource();

}
