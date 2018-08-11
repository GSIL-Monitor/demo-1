package com.example.lunzi.spring.core.io.type.classreading;

import com.example.lunzi.spring.core.io.Resource;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

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

    public SimpleMetadataReader(Resource resource) {
        this.resource = resource;
        try {
            ClassReader reader = new ClassReader(resource.getInputStream());
            ClassMetadataReadingVisitor classMetadataReadingVisitor = new ClassMetadataReadingVisitor();
            AnnotationMetadataReadingVisitor annotationMetadataReadingVisitor = new AnnotationMetadataReadingVisitor();
            reader.accept(classMetadataReadingVisitor, ClassReader.SKIP_DEBUG);
            reader.accept(annotationMetadataReadingVisitor, ClassReader.SKIP_DEBUG);
            this.classMetadata = classMetadataReadingVisitor;
            this.annotationMetadata = annotationMetadataReadingVisitor;
        } catch (IOException e) {
            e.printStackTrace();
        }

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
