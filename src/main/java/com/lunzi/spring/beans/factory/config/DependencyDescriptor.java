package com.lunzi.spring.beans.factory.config;

import java.lang.reflect.Field;

/**
 * 依赖描述，用来支持注解的依赖，主要是@Aotowire
 * @Author suosong
 * @Date 2018/8/13
 */
public class DependencyDescriptor {
    Field field;
    boolean require;

    public DependencyDescriptor(Field field, boolean require) {
        this.field = field;
        this.require = require;
    }

    public Class getDependencyType() {
        if(this.field != null){
            return field.getType();
        }
        throw new RuntimeException("field is null");
    }

    public boolean isRequire() {
        return require;
    }
}
