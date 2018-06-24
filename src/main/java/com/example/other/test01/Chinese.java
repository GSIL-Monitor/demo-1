package com.example.other.test01;

import org.springframework.stereotype.Component;

@Component
public class Chinese implements Person{
    @Override
    public void sayHello() {
        System.out.println("中国");
    }
}
