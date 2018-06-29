package com.example.lunzi.spring;

import com.example.lunzi.spring.beans.Person;
import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.BeanFactory;
import com.example.lunzi.spring.beans.factory.support.AnnotationBeanFactory;
import com.example.lunzi.spring.testconfig.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class Test01 {

    @Before
    public void setup(){
        //org.springframework.beans.factory.BeanFactory
        //GenericBeanDefinition
        //org.springframework.beans.factory.config.BeanDefinition
        //BeanDefinitionReader
    }

    @Test
    public void test_getBean() {
        BeanFactory factory = new AnnotationBeanFactory(TestConfig.class);
        BeanDefinition beanDefinition = factory.getBeanDefinition("person");
        Assert.assertEquals("com.example.lunzi.spring.beans.Person",beanDefinition.getClassName());
        Object person = factory.getBean("person");
        Assert.assertNotNull(person);
        Assert.assertEquals("xiaoming",((Person)person).getName());
    }

    /**
     * 测试异常
     */
    @Test
    public void test_beanCreationException(){
        BeanFactory factory = new AnnotationBeanFactory(TestConfig.class);

        try {
            factory.getBean("badPerson");
        } catch (BeanCreationException e) {
            e.printStackTrace();
            return;
        }
        Assert.fail();
    }

}
