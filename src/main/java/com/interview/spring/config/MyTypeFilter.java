package com.interview.spring.config;

import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 包扫描策略
 * 看到了熟悉的metadataReader
 * 可以推断出来，这个是用asm来解刨类的
 * @Author suosong
 * @Date 2018/8/14
 */
public class MyTypeFilter implements TypeFilter{
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        String className = classMetadata.getClassName();
        if(className.contains("Mother")){
            return false;
        }
        return true;
    }
}
