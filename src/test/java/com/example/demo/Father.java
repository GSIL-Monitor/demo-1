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
}
