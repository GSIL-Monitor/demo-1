package com.example.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Scope("prototype")
public class TestService {
    public TestService() {
        System.out.println("构造方法执行了 TestService");
    }

    @PostConstruct
    public void sayHello(){
        System.out.println("===say hello");
    }
    @PreDestroy
    public void cleanup(){
        System.out.println("==clean up");
    }
}
