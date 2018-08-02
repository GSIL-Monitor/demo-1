package com.example.lunzi.spring;

import com.example.lunzi.spring.context.ApplicationContext;
import com.example.lunzi.spring.context.support.ClasspathXmlApplicationContext;
import com.example.lunzi.spring.context.support.FileSystemXmlApplicationContext;
import com.example.lunzi.spring.testconfig.TestConfig;
import com.example.other.test01.Chinese;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class ApplicationContextTest {

    ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new ClasspathXmlApplicationContext("spring/spring-context.xml");
    }

    @Test
    public void test_getbean() {
        Object obj = applicationContext.getBean("person");
        Assert.assertNotNull(obj);
        System.out.println(obj);
    }

    @Test
    public void test_getbean02() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/Users/peter/suosong/git/demo/src/main/resources/spring/spring-context.xml");
        Object obj = applicationContext.getBean("person");
        Assert.assertNotNull(obj);
        System.out.println(obj);
    }

    @Test
    public void test_injection() {
        Chinese person = (Chinese) applicationContext.getBean("person");
        Assert.assertNotNull(person.getUserName() );
        Assert.assertNotNull(person.getAccount());


    }


}
