package com.example.lunzi.spring.beans.factory.config;

/**
 * 描述ref 的类
 * @Author suosong
 * @Date 2018/7/16
 */
public class RuntimeBeanReference {

    String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }


}
