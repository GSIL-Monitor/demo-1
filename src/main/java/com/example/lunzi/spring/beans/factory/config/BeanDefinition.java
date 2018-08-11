package com.example.lunzi.spring.beans.factory.config;

import java.util.List;

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

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    //是否有构造方法
    boolean hasConstructorArgumentValues();

    String getBeanName();//获得bean id

    void setBeanClassName(String beanClassName);

    void setBeanName(String beanName);


}
