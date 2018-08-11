package com.example.lunzi.spring;

import com.example.lunzi.spring.core.io.ClassPathResource;
import com.example.lunzi.spring.core.io.Resource;
import com.example.lunzi.spring.core.io.type.classreading.AnnotationMetadataReadingVisitor;
import com.example.lunzi.spring.stereotypa.Component;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;

/**
 * 解析注解 测试
 * @Author suosong
 * @Date 2018/8/10
 */
@Component("suosong")
public class AnnotationVisitorTest {

    @Test
    public void test() throws IOException {

        Resource  resource = new ClassPathResource("com/example/lunzi/spring/AnnotationVisitorTest.class");

        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();

        reader.accept(visitor,ClassReader.SKIP_DEBUG);

        Assert.assertTrue(visitor.hasAnnotation("com.example.lunzi.spring.stereotypa.Component"));

        Assert.assertEquals("suosong",
                visitor.getAnnotationAttributeValue("com.example.lunzi.spring.stereotypa.Component","value"));

    }
}
