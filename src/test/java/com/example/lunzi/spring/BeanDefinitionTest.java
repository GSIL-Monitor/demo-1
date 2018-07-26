package com.example.lunzi.spring;

import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.example.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.lunzi.spring.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/7/16
 */
public class BeanDefinitionTest {
    private BeanDefinition beanDefinition ;
    @Before
    public void setup(){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("spring/spring-context.xml"));
        this.beanDefinition = defaultBeanFactory.getBeanDefinition("person");
    }

    @Test
    public void test_Property(){
        Assert.assertTrue(beanDefinition.getPropertyValues().size() == 2);
    }
}
