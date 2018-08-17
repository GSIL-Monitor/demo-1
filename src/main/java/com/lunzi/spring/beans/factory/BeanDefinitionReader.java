package com.lunzi.spring.beans.factory;

import com.lunzi.spring.core.io.Resource;

/**
 * 读取配置文件
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;
}
