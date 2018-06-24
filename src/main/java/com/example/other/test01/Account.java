package com.example.other.test01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Account {
    @Autowired
    @Qualifier("chinese")
    Person person;

    @Resource(name="japanese")
    Person person1;

    public void say(){
        person1.sayHello();
    }
}
