package com.example.lunzi.spring;

import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.support.BeanDefinitionRegistry;
import com.example.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.example.lunzi.spring.context.annotation.ClasspathBeanDefinitionScanner;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Set;

/**
 * 测试包扫描类
 * @Author suosong
 * @Date 2018/8/11
 */
public class ClasspathBeanDefinitionScannerTest {

    @Test
    public void test() throws IOException {

        BeanDefinitionRegistry registry = new DefaultBeanFactory();

        ClasspathBeanDefinitionScanner scanner = new ClasspathBeanDefinitionScanner(registry);

        Set<BeanDefinition> beanDefinitions = scanner.doScan("com.example.demo");

        Assert.assertEquals(beanDefinitions.size(), 2);

    }
}
