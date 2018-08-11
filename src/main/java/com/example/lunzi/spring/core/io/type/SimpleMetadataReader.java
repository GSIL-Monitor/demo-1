package com.example.lunzi.spring.core.io.type;

import com.example.lunzi.spring.core.io.Resource;
import com.example.lunzi.spring.core.io.type.AnnotationMetadata;
import com.example.lunzi.spring.core.io.type.ClassMetadata;
import com.example.lunzi.spring.core.io.type.MetadataReader;
import com.example.lunzi.spring.core.io.type.classreading.AnnotationMetadataReadingVisitor;
import com.example.lunzi.spring.core.io.type.classreading.ClassMetadataReadingVisitor;
import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 此类存在的目的就是把asm的操作给隐藏掉
 *
 * @Author suosong
 * @Date 2018/8/10
 */
public class SimpleMetadataReader implements MetadataReader {

    ClassMetadata classMetadata;
    AnnotationMetadata annotationMetadata;
    Resource resource;

    public SimpleMetadataReader(Resource resource) throws IOException {

        InputStream inputStream = resource.getInputStream();
        ClassReader reader;
        try {
            reader = new ClassReader(resource.getInputStream());
        } finally {
            inputStream.close();
        }
        ClassMetadataReadingVisitor classMetadataReadingVisitor = new ClassMetadataReadingVisitor();
        AnnotationMetadataReadingVisitor annotationMetadataReadingVisitor = new AnnotationMetadataReadingVisitor();
        reader.accept(classMetadataReadingVisitor, ClassReader.SKIP_DEBUG);
        reader.accept(annotationMetadataReadingVisitor, ClassReader.SKIP_DEBUG);
        this.classMetadata = classMetadataReadingVisitor;
        this.annotationMetadata = annotationMetadataReadingVisitor;
        this.resource = resource;


    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }
}
