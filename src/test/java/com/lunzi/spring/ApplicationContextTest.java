package com.lunzi.spring;

import com.example.demo.Father;
import com.lunzi.spring.context.ApplicationContext;
import com.lunzi.spring.context.support.ClasspathXmlApplicationContext;
import com.lunzi.spring.context.support.FileSystemXmlApplicationContext;
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

    /**
     * 测试注解依赖注入
     */
    @Test
    public void test_autowired(){
        Father father = (Father) applicationContext.getBean("father");
        Assert.assertNotNull(father.getMother());
    }



}
