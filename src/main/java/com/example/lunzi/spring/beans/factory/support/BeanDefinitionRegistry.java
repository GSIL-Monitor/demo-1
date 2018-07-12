package com.example.lunzi.spring.beans.factory.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;

/**
 * 注册beandefiniton 功能。
 * 问题一，为什么要放在这个包下面
 * 问题二，为什么要把注册bean definition单独用接口拿出来。
 *
 * 这个东西说起来话长，按照单一职责原则，DefaultBeanFactory要把获得BeanDefinitionMap 功能 跟通过这个Map 利用反射获得bean对象给抽取出来
 * 于是就有了AnnotationBeanDefinitionReader类来获得一个个的definiton对象。 获得后，需要将其放入factory的map中，这样就需要持有factory对象
 * 但是DefalutFacotry可能有很多功能，不希望持有他的类看到其所有的功能，那么接口的作用就显现出来了。比较典型的是，客户端调用BeanFactory对象时，
 * 只看到BeanFactory接口中定义的功能就可以了，不希望看到其他的。但是DefaultFactory确实是个比较大的类。
 *
 * 至于为啥放在这个包下面，因为这样，这个接口并不是那种很多类都实现的。这个接口是为了规范一个具体实现对象的。是跟强实现相关的，所以把他跟其实现类放在一个
 * 包下面。
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException;

    BeanDefinition getBeanDefinition(String person);

}
