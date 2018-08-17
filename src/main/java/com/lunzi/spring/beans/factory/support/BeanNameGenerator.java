package com.lunzi.spring.beans.factory.support;

import com.lunzi.spring.beans.factory.config.BeanDefinition;

/**
 * 根据一定的规则，生成bean 的name
 * @Author suosong
 * @Date 2018/8/11
 */
public interface BeanNameGenerator {
    //很多情况下，第二个参数是用不到的
    String generateBeanName(BeanDefinition beanDefinition,BeanDefinitionRegistry beanDefinitionRegistry);
}
