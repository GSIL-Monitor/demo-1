package com.example.spring.annotation;

import com.example.spring.annotation.beans.Male;
import com.example.spring.annotation.beans.Person;
import com.example.spring.annotation.config.ConfigFile;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class Test01 {

    @Test
    public void test_bean(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigFile.class);
        Person person =  (Person) applicationContext.getBean("person02");
        System.out.println(person);

        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for(String name : names){
            System.out.println(name);
        }
    }
}
