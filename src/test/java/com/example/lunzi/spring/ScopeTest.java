package com.example.lunzi.spring;

import com.example.lunzi.spring.context.ApplicationContext;
import com.example.lunzi.spring.context.support.FileSystemXmlApplicationContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/7/12
 */
public class ScopeTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("/Users/peter/suosong/git/demo/src/main/resources/spring/spring-context.xml");
        Object obj = applicationContext.getBean("person");
        Object obj2 = applicationContext.getBean("person");
        Assert.assertNotNull(obj);
        Assert.assertEquals(obj, obj2);

        Object obj3 = applicationContext.getBean("japanese");
        Object obj4 = applicationContext.getBean("japanese");
        Assert.assertNotNull(obj3);
        Assert.assertNotEquals(obj3, obj4);

    }
}
