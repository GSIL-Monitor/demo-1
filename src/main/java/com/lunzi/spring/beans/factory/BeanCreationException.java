package com.lunzi.spring.beans.factory;

import com.lunzi.spring.beans.BeansException;

/**
 * 当从beandefinition 中创建bean对象出错时抛出
 * @Author suosong
 * @Date 2018/6/28
 */
public class BeanCreationException extends BeansException{
    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
