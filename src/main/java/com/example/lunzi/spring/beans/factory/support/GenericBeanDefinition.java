package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String name;
    private String beanClassName;

    private String scope = SCOPE_DEFAULT;
    private boolean singleton = true;
    private boolean prototype = false;

    public GenericBeanDefinition(String name, String beanClassName) {
        this.name = name;
        this.beanClassName = beanClassName;
    }



    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }


    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);//有点技巧性
    }
}
