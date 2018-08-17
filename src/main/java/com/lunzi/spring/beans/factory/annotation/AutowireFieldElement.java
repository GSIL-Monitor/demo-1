package com.lunzi.spring.beans.factory.annotation;

import com.lunzi.spring.beans.factory.BeanCreationException;
import com.lunzi.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.lunzi.spring.beans.factory.config.DependencyDescriptor;
import com.lunzi.spring.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

/**
 * @Author suosong
 * @Date 2018/8/13
 */
public class AutowireFieldElement extends InjectionElement {
    boolean require;

    public AutowireFieldElement(boolean require,Member member, AutowireCapableBeanFactory autowireCapableBeanFactory) {
        super(member, autowireCapableBeanFactory);
        this.require = require;
    }

    @Override
    public void inject(Object target) {
        Field field = (Field) super.getMember();

        try {
            DependencyDescriptor descriptor = new DependencyDescriptor(field,this.require);
            Object obj = super.getAutowireCapableBeanFactory().resolveDependency(descriptor);
            ReflectionUtils.makeAccessible(field);//破坏一下类的封装性,是私有的才破坏。工具类做了一下判断
            field.set(target,obj);
        } catch (Exception e) {
            throw new BeanCreationException("error ",e);
        }

    }
}
