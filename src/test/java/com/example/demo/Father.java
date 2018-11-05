package com.example.demo;

import com.lunzi.spring.beans.factory.annotation.AutoWired;
import com.lunzi.spring.stereotypa.Component;

/**
 * @Author suosong
 * @Date 2018/8/11
 */
@Component
public class Father {

    @AutoWired(required = "true")
    Mother mother;

    public Mother getMother() {
        return mother;
    }

    public static void main(String[] args) {
        System.out.println(7 * 24 * 60 * 60 * 1000);
    }
}
