package com.example.lunzi.spring.beans;

/**
 * 所有Bean   exception 的父类
 * @Author suosong
 * @Date 2018/6/28
 */
public class BeansException extends RuntimeException {

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
