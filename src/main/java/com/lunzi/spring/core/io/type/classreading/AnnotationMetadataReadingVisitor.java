package com.lunzi.spring.core.io.type.classreading;


import com.lunzi.spring.core.io.type.AnnotationMetadata;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 读取注解信息的观察者
 *
 * @Author suosong
 * @Date 2018/8/10
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {


    Map<String,AnnotationAttributes> attributesMap = new HashMap<>();

    /**
     * 之所以要返回一个AnnotationVisitor对象，是因为Visitor框架对这个对象还有一次调用，比较难以理解。
     * 之所以要这么干，是因为注解可能都不止一个属性，每一次解析属性，都需要来个visit。
     * 总结，每遇到一个注解，走一次ClassReader的visitAnnotation
     * 每解析注解的其中一个属性，走一次AnnotationVisitor的visitor
     * @param desc
     * @param visible
     * @return
     */
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("desc=" + desc);
        String className = Type.getType(desc).getClassName();

        return new AnnotationAttributesReadingVisitor(className,this.attributesMap);

    }

    @Override
    public boolean hasAnnotation(String annotationClassName){
        return this.attributesMap.keySet().contains(annotationClassName);
    }

    @Override
    public Object getAnnotationAttributeValue(String annotationValue ,String attributeName){
        AnnotationAttributes attributes = this.attributesMap.get(annotationValue);
        if(attributes != null){
            return attributes.get(attributeName);
        }
        return null;
    }

    @Override
    public Set<String> getAnnotationTypes() {
        return this.attributesMap.keySet();
    }




}
