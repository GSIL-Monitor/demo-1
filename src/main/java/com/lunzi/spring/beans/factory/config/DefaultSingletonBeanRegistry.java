package com.lunzi.spring.beans.factory.config;

import com.lunzi.spring.utils.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author suosong
 * @Date 2018/7/12
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>(64);

    @Override
    public void registerSingleton(String beanName, Object obj) {
        Assert.notNull(beanName, "beanName 不能为空");
        Object oldObj = singletonObjects.get(obj);
        if (null != oldObj) {//重复.这里可以看做是spring的一个默认很强的约定。
            throw new IllegalStateException("有重复Singleton beanName:" + beanName);
        }
        singletonObjects.put(beanName, obj);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
}
