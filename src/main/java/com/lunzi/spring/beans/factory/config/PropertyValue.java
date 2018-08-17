package com.lunzi.spring.beans.factory.config;

/**
 * xml配置中对应的 <bean><property></property></bean>
 *
 * @Author suosong
 * @Date 2018/7/16
 */
public class PropertyValue {
    String name;
    Object value;//value或者ref的值,之所有用Object ，那是因为其有两种可能，RuntimeBeanReference或者 TypeString。这个也是一种编程技巧

    Object convertedObject;//如果是ref 则将其转化为bean放到此字段

    boolean isConberted = false;//是否经过转化

    public boolean isConberted() {
        return isConberted;
    }

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getConvertedObject() {
        return convertedObject;
    }

    public Object getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
