package com.example.lunzi.spring;

import com.example.lunzi.spring.beans.Person;
import com.example.lunzi.spring.beans.factory.BeanCreationException;
import com.example.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.config.BeanDefinition;
import com.example.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.example.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    BeanDefinitionReader reader = null;

    @Before
    public void setup(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
        //reader.loadBeanDefinitions("spring/spring-context.xml");
    }

    @Test
    public void test_getBean() {
        BeanDefinition beanDefinition = factory.getBeanDefinition("person");
        Assert.assertEquals("com.example.lunzi.spring.beans.Person",beanDefinition.getBeanClassName());
        Object person = factory.getBean("person");
        Assert.assertNotNull(person);
        Assert.assertEquals("xiaoming",((Person)person).getName());
    }

    /**
     * 测试异常
     */
    @Test
    public void test_beanCreationException(){

        try {
            factory.getBean("badPerson");
        } catch (BeanCreationException e) {
            e.printStackTrace();
            return;
        }
        Assert.fail();
    }

}
