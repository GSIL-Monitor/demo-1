package com.example.other.test01;

import org.springframework.stereotype.Component;

@Component
public class Japanese implements Person {
    @Override
    public void sayHello() {
        System.out.println("日本");
    }
}
