package com.example.lunzi.spring.beans.factory.config;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public interface BeanDefinition {


    static final String SCOPE_SINGLETON = "singleton";
    static final String SCOPE_PROTOTYPE = "prototype";
    static final String SCOPE_DEFAULT = "";

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    String getBeanClassName();


}
