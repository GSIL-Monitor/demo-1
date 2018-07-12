package com.example.lunzi.spring.context.support;

import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.example.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.lunzi.spring.context.ApplicationContext;
import com.example.lunzi.spring.core.io.FileSystemResource;
import com.example.lunzi.spring.core.io.Resource;

/**
 * @Author suosong
 * @Date 2018/7/11
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext{

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(this.getBeanClassLoader(),path);
    }
}
