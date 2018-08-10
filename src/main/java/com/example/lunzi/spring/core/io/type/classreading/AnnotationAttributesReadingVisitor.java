package com.example.lunzi.spring.core.io.type.classreading;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Opcodes;

import java.util.HashMap;
import java.util.Map;

/**
 * 此类用来解析注解属性
 *
 * @Author suosong
 * @Date 2018/8/10
 */
public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {


    String className;//注解名
    Map<String, AnnotationAttributes> attributesMap = new HashMap<>();//注解属性
    AnnotationAttributes attributes = new AnnotationAttributes();//也是注解属性，包含的关系

    public AnnotationAttributesReadingVisitor(String className, Map<String, AnnotationAttributes> attributesMap) {

        super(Opcodes.ASM4);
        this.className = className;
        this.attributesMap = attributesMap;
    }


    /**
     * @param name  注解属性名
     * @param value 注解属性值
     */
    @Override
    public void visit(String name, Object value) {
        attributes.put(name,value);
    }

    /**
     * 容易出问题，一个class 有多个同样的注解，只会保留最后一个
     */
    @Override
    public void visitEnd() {
        this.attributesMap.put(className,attributes);
    }
}
