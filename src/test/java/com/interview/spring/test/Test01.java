package com.interview.spring.test;

import com.interview.spring.bean.Person;
import com.interview.spring.config.Config01;
import org.apache.commons.logging.impl.SimpleLog;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

/**
 * @Author suosong
 * @Date 2018/8/14
 */
public class Test01 {

    /**
     * 测试spring容器初始化时，有哪些默认的bean加到容器中了
     */
    @Test
    public void test_allBeanDefinitions(){
        //SimpleLog

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01.class);
        System.out.println("===============");
        int beansCount = applicationContext.getBeanDefinitionCount();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : beanNames){
            System.out.println(beanName );
        }
        System.out.println("===============");
        System.out.println("beansCount="+beansCount);

        //applicationContext.getBeanNamesForType()

        applicationContext.getBean("person");

    }

    @Test
    public void test_allBeanNamesByType(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config01.class);
        System.out.println("===============");
        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for(String name : names){
            System.out.println(name);
        }
    }
}
