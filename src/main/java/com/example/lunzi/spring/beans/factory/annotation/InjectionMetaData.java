package com.example.lunzi.spring.beans.factory.annotation;

import java.util.List;

/**
 * 将注解 注入过程，封装为一个类
 *
 * @Author suosong
 * @Date 2018/8/13
 */
public class InjectionMetaData {
    //Class targetClass;
    List<InjectionElement> injectionElements;

    public InjectionMetaData( List<InjectionElement> injectionElements) {

        this.injectionElements = injectionElements;
    }

    public void inject(Object target) {
        if (this.injectionElements == null || injectionElements.isEmpty()) return;
        for (InjectionElement ele : this.injectionElements) {
            ele.inject(target);
        }
    }
}
