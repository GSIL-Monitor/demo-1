package com.lunzi.spring;

import com.lunzi.spring.beans.Person;
import com.lunzi.spring.beans.factory.BeanCreationException;
import com.lunzi.spring.beans.factory.BeanDefinitionReader;
import com.lunzi.spring.beans.factory.config.BeanDefinition;
import com.lunzi.spring.beans.factory.config.ConstructorArgument;
import com.lunzi.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.lunzi.spring.beans.factory.support.DefaultBeanFactory;
import com.lunzi.spring.core.io.ClassPathResource;
import com.lunzi.spring.core.io.Resource;
import com.example.other.test01.Cat;
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
        Resource resource = new ClassPathResource("spring/spring-context.xml");
        reader.loadBeanDefinitions(resource);
    }

    @Test
    public void test_getBean() {
        BeanDefinition beanDefinition = factory.getBeanDefinition("person");
        Assert.assertEquals("com.lunzi.spring.beans.Person",beanDefinition.getBeanClassName());
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

    /**
     * 测试构造器注入
     */
    @Test
    public void test_constructor(){
        BeanDefinition bd = factory.getBeanDefinition("cat");
        ConstructorArgument constructorArgument = bd.getConstructorArgument();
        Assert.assertSame(3,constructorArgument.getValueHoders().size());

        Object obj = factory.getBean("cat");
        Assert.assertNotNull(obj);

        Cat cat = (Cat) obj;
        Assert.assertTrue("花花".equals(cat.getName()));
    }

}
