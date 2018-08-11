package com.example.lunzi.spring;

import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.example.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.example.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.lunzi.spring.core.io.ClassPathResource;
import com.example.lunzi.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/8/11
 */
public class XmlBeanDefinitionReaderTest {

    /**
     * 测试注解
     */
    @Test
    public void test_annotation(){
        Resource resource = new ClassPathResource("spring/spring-context.xml");

        BeanDefinitionRegistry registry = new DefaultBeanFactory();

        BeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);

        reader.loadBeanDefinitions(resource);

        BeanDefinition bd = registry.getBeanDefinition("mother");

        Assert.assertNotNull(bd);
    }
}
