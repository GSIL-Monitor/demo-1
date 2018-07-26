package com.example.other.test02;

/**
 * @Author suosong
 * @Date 2018/7/20
 */
public class LongTest {
    public static void main(String[] args) {
        Long abc = 123L;
        System.out.println(abc==123);

        System.out.println(get());

        Integer integer = Integer.valueOf(5);
    }

    private static String get(){
        try {
            return "try";
        } finally {
            return "finally";
        }
    }
}
