package com.example.lunzi.spring.beans.factory;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.core.io.Resource;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 读取配置文件
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException;
}
