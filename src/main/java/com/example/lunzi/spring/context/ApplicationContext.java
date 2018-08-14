package com.example.lunzi.spring.context;

import com.example.lunzi.spring.beans.factory.BeanFactory;
import com.example.lunzi.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * 是对BeanFactory的扩展
 * 由此可见，spring-context包依赖 spring-beans包
 */
public interface ApplicationContext extends BeanFactory{

}
