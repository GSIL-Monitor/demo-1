package com.example.lunzi.spring.beans.factory.annotation;

import com.example.lunzi.spring.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

/**
 * 被注入的元素
 * 之所以是个抽象类，这个是因为属性注入跟方法注入的实现是不一样的。
 * @Author suosong
 * @Date 2018/8/13
 */
public abstract class InjectionElement {
    Member member;
    AutowireCapableBeanFactory autowireCapableBeanFactory;

    public InjectionElement(Member member, AutowireCapableBeanFactory autowireCapableBeanFactory) {
        this.member = member;
        this.autowireCapableBeanFactory = autowireCapableBeanFactory;
    }

    public Member getMember() {
        return member;
    }

    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() {
        return autowireCapableBeanFactory;
    }

    public abstract void inject(Object target);
}
