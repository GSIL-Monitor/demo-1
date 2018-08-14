package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.config.ConstructorArgument;
import com.example.lunzi.spring.beans.factory.config.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class GenericBeanDefinition implements BeanDefinition {
    private String name;
    private String beanClassName;
    private Class beanClass;

    private String scope = SCOPE_DEFAULT;
    private boolean singleton = true;
    private boolean prototype = false;

    private List<PropertyValue> propertyValues = new ArrayList<>();

    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBeanClassName(String beanClassName)  {
        this.beanClassName = beanClassName;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
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

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgument.isEmpty();
    }

    @Override
    public String getBeanName() {
        return this.name;
    }

    @Override
    public void setBeanName(String beanName) {
        this.name = beanName;
    }

    @Override
    public Class getBeanClass() {
        return this.beanClass;
    }

    @Override
    public void resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
        this.beanClass = classLoader.loadClass(this.beanClassName);
    }

    @Override
    public boolean hasBeanClass() {
        return this.beanClass != null;
    }
}
