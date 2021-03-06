package com.lunzi.spring;

import com.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.lunzi.spring.beans.factory.config.BeanDefinition;
import com.lunzi.spring.beans.factory.config.RuntimeBeanReference;
import com.lunzi.spring.beans.factory.support.BeanDefinitionValueResolver;
import com.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lunzi.spring.core.io.ClassPathResource;
import com.example.other.test01.Chinese;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @Author suosong
 * @Date 2018/7/26
 */
public class BeanDefinitionValueResolverTest {
    private BeanDefinition beanDefinition ;
    @Before
    public void setup(){

    }
    @Test
    public void test(){
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinitions(new ClassPathResource("spring/spring-context.xml"));

        RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference("person");

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(defaultBeanFactory);

        Object object = resolver.resolveValueIfNecessary(runtimeBeanReference);

        Assert.assertTrue(object instanceof Chinese);

    }
}
