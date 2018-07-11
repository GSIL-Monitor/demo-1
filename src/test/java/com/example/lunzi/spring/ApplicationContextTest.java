package com.example.lunzi.spring;

import com.example.lunzi.spring.context.ApplicationContext;
import com.example.lunzi.spring.context.support.AnnotationConfigApplicationContext;
import com.example.lunzi.spring.testconfig.TestConfig;
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
    public void setup(){
        applicationContext = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    @Test
    public void test_getbean(){
        Object obj = applicationContext.getBean("person");
        Assert.assertNotNull(obj);
    }



}
