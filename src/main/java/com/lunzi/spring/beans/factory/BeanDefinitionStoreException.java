package com.lunzi.spring.beans.factory;

import com.lunzi.spring.beans.BeansException;

/**
 * 当从xml或者注解中读取配置数据 抽象为definition 数据结构 出错时抛出
 * @Author suosong
 * @Date 2018/6/28
 */
public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
