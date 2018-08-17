package com.lunzi.spring.core.io.type.classreading;

import java.util.LinkedHashMap;

/**
 * 注解属性，对于Map的包装，有了直接去固定类型的能力
 * @Author suosong
 * @Date 2018/8/10
 */
public class AnnotationAttributes extends LinkedHashMap<String,Object> {

    public String getStringAttribute(String name){
        return (String) get(name);
    }
}
