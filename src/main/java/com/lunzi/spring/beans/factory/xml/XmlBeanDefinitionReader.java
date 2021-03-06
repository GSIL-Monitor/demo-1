package com.lunzi.spring.beans.factory.xml;

import com.lunzi.spring.beans.factory.BeanDefinitionStoreException;
import com.lunzi.spring.beans.factory.config.ConstructorArgument;
import com.lunzi.spring.beans.factory.config.PropertyValue;
import com.lunzi.spring.beans.factory.config.RuntimeBeanReference;
import com.lunzi.spring.beans.factory.config.TypeStringValue;
import com.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.lunzi.spring.beans.factory.support.GenericBeanDefinition;
import com.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.lunzi.spring.context.annotation.ClasspathBeanDefinitionScanner;
import com.lunzi.spring.core.io.Resource;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;


import java.io.IOException;
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

    private final String NAME_ATTRIBUTE = "name";
    private final String CLASS_ATTRIBUTE = "class";
    private final String SCOPE_ATTRIBUTE = "scope";
    private final String REF_ATTRIBUTE = "ref";
    private final String VALUE_ATTRIBUTE = "value";
    private final String PROPERTY_ELEMENT = "property";
    private final String CONSTRUCTOR_ARG = "constructor-arg";

    private final String COMPONENT_SCAN = "component-scan";
    private final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    private final String DEFAULT_NAMESPACE_URI = "http://www.springframework.org/schema/beans";
    private final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";

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
                Element ele = it.next();
                String nameSpaceUri = ele.getNamespaceURI();
                if(isDefaultNameSpaceUri(nameSpaceUri)){
                    parseDefaultElement(ele);
                }else if(isContextNameSpaceUri(nameSpaceUri)){
                    parseComponentElement(ele);
                }
            }

        } catch (Exception e) {
            throw new BeanDefinitionStoreException("xml解析失败", e);
        }

    }

    /**
     * 解析注解
     * @param ele
     */
    private void parseComponentElement(Element ele) throws IOException {
        if(ele.getName().equals(COMPONENT_SCAN)){
            String packageToScan = ele.attributeValue(BASE_PACKAGE_ATTRIBUTE);
            ClasspathBeanDefinitionScanner scanner = new ClasspathBeanDefinitionScanner(registry);
            scanner.doScan(packageToScan);
        }

    }

    /**
     * 标签前缀是否为context
     * @param nameSpaceUri
     * @return
     */
    private boolean isContextNameSpaceUri(String nameSpaceUri) {
        return CONTEXT_NAMESPACE_URI.equals(nameSpaceUri);
    }

    private void parseDefaultElement(Element ele) throws ClassNotFoundException {
        String name = ele.attributeValue(NAME_ATTRIBUTE);
        String className = ele.attributeValue(CLASS_ATTRIBUTE);
        String scope = ele.attributeValue(SCOPE_ATTRIBUTE);
        if (null == scope) scope = "";
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setName(name);
        definition.setBeanClassName(className);
        definition.setScope(scope);//这里必须用set方法，而不能放到构造函数中去
        //解析<property>标签
        parseProperty(ele, definition);
        //解析<constructor-arg>标签
        parseConstructorArgument(ele,definition);
        //注册
        this.registry.registerBeanDefinition(name, definition);
    }

    /**
     * 是否为默认的，也就是 标签前缀为bean
     * @param nameSpaceUri
     * @return
     */
    private boolean isDefaultNameSpaceUri(String nameSpaceUri) {
        return DEFAULT_NAMESPACE_URI.equals(nameSpaceUri);
    }

    /**
     * 解析property标签
     *
     * @param beanEle
     * @param definition
     */
    private void parseProperty(Element beanEle, GenericBeanDefinition definition) {
        Iterator<Element> eleIterator = beanEle.elementIterator(PROPERTY_ELEMENT);
        while (eleIterator.hasNext()) {
            Element proElement = eleIterator.next();
            String proName = proElement.attributeValue(NAME_ATTRIBUTE);
            String proRef = proElement.attributeValue(REF_ATTRIBUTE);
            String proValue = proElement.attributeValue(VALUE_ATTRIBUTE);
            if (proRef != null) {
                RuntimeBeanReference rf = new RuntimeBeanReference(proRef);
                PropertyValue pv = new PropertyValue(proName,rf);
                definition.getPropertyValues().add(pv);
            } else if(proValue != null){
                TypeStringValue tsv = new TypeStringValue(proValue);
                PropertyValue pv = new PropertyValue(proName,tsv);
                definition.getPropertyValues().add(pv);
            }else {
                //既没有ref 又没有 value
                throw new IllegalStateException("xml 缺少参数");
            }
        }
    }

    /**
     * 解析构造函数
     * @param beanEle
     * @param definition
     */
    private void parseConstructorArgument(Element beanEle, GenericBeanDefinition definition) {

        Iterator<Element> elementIterator = beanEle.elementIterator(CONSTRUCTOR_ARG);
        while(elementIterator.hasNext()){
            Element consEle = elementIterator.next();
            String name = consEle.attributeValue(NAME_ATTRIBUTE);
            String  ref = consEle.attributeValue(REF_ATTRIBUTE);
            String value = consEle.attributeValue(VALUE_ATTRIBUTE);
            if (ref != null) {
                RuntimeBeanReference rf = new RuntimeBeanReference(ref);
                ConstructorArgument.ValueHoder vh = new ConstructorArgument.ValueHoder(name,rf);
                definition.getConstructorArgument().addArgument(vh);
            } else if(value != null){
                TypeStringValue tsv = new TypeStringValue(value);
                ConstructorArgument.ValueHoder vh = new ConstructorArgument.ValueHoder(name,tsv);
                definition.getConstructorArgument().addArgument(vh);
            }else {
                //既没有ref 又没有 value
                throw new IllegalStateException("xml 缺少参数");
            }
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
            //GenericBeanDefinition definition = new GenericBeanDefinition(name, className, methodName);
            //注册
            //this.registry.registerBeanDefinition(name, definition);
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
