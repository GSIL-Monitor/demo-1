package com.example.service;

import com.DemoApplication;
import com.example.other.test01.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class TestServiceTest {


    @Test
    public void testScope() {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoApplication.class);
        TestService testService01 = (TestService) applicationContext.getBean("testService");
        TestService testService02 = (TestService) applicationContext.getBean("testService");
        assertEquals(false, testService01 == testService02);

    }

    /**
     * 测试init 跟 destroy 方法
     * 只有scope是prototype时才能测试
     */
    @Test
    public void testInit() throws InterruptedException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoApplication.class);
        TestService testService01 = (TestService) applicationContext.getBean("testService");
        TestService testService02 = (TestService) applicationContext.getBean("testService");
        testService01 = null;
        testService02 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    /**
     * 测试@Autowired
     */
    @Test
    public void testAutowired(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DemoApplication.class);
        Account account = (Account) applicationContext.getBean("account");
        account.say();
    }
}