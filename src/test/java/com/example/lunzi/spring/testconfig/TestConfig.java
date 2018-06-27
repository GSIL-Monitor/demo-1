package com.example.lunzi.spring.testconfig;

import com.example.lunzi.spring.beans.Person;
import org.springframework.context.annotation.Bean;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class TestConfig {
    @Bean
    public Person person(){
        return new Person("xiaoming",30);
    }
}
