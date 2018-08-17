package com.lunzi.spring.testconfig;

import com.lunzi.spring.beans.BadPerson;
import com.lunzi.spring.beans.Person;
import org.springframework.context.annotation.Bean;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class TestConfig {
    /**
     * 使用反射生成的对象
     * @return
     */
    @Bean
    public Person person(){
        return new Person("xiaoming",30);
        //return null;
    }

    @Bean
    public BadPerson badPerson(){
        return new BadPerson();
        //return null;
    }
}
