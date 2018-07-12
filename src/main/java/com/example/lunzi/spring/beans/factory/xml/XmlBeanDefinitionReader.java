package com.example.lunzi.spring.beans.factory.xml;

import com.example.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.example.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.example.lunzi.spring.beans.factory.support.GenericBeanDefinition;
import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.example.lunzi.spring.core.io.Resource;
import com.example.lunzi.spring.utils.Assert;
import com.example.lunzi.spring.utils.ClassUtils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;



import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * 读取注解资料，单一职责原则
 *
 * @Author suosong
 * @Date 2018/6/28
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        //Assert.notNull(path, "xml路径不能为空");

        try {
            InputStream inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();
            Iterator<Element> it = root.elementIterator();
            while (it.hasNext()) {
                Element beanEle = it.next();
                String name = beanEle.attributeValue("name");
                String className = beanEle.attributeValue("class");
                GenericBeanDefinition definition = new GenericBeanDefinition(name, className);
                //注册
                this.registry.registerBeanDefinition(name, definition);
            }

        } catch (Exception e) {
            throw new BeanDefinitionStoreException("xml解析失败", e);
        }

    }

    /**
     * 读注解
     *
     * @param clazz
     * @throws BeanDefinitionStoreException
     */
    public void loadBeanDefinitions(Class<?> clazz) throws BeanDefinitionStoreException {
        Method[] methods = clazz.getMethods();
        if (methods == null) return;
        for (Method method : methods) {
            Bean bean = method.getAnnotation(Bean.class);
            if (bean == null) continue;
            String name = method.getName();
            String className = method.getReturnType().getName();
            String methodName = method.getName();
            GenericBeanDefinition definition = new GenericBeanDefinition(name, className, methodName);
            //注册
            this.registry.registerBeanDefinition(name, definition);
        }

       /* //下面是私自加上的
        try {
            this.registry.setConfigObj(clazz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }


}
