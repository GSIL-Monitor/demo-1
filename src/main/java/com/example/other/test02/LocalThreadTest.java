package com.example.other.test02;

/**
 * @Author suosong
 * @Date 2018/7/18
 */
public class LocalThreadTest {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(5);
        threadLocal.get();
    }
}
