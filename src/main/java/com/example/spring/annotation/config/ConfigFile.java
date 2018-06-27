package com.example.spring.annotation.config;

import com.example.spring.annotation.beans.Male;
import com.example.spring.annotation.beans.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
@Configuration
@ComponentScan("com.example.spring.annotation.beans")
public class ConfigFile {


    @Bean("person01")//给容器注册一个bean，相当于xml中bean标签,如果不写名称，默认是方法名
    Person person01(){
        return new Person("小忙",34);
    }
    @Bean("person02")
    Person person02(){
        return new Person("小松",12);
    }
    @Bean
    Male male(){
        return new Male();
    }

}
