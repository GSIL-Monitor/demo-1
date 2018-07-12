package com.example.lunzi.spring.utils;

/**
 * @Author suosong
 * @Date 2018/7/11
 */
public class Assert {

    public static void notNull( Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notEmpty( Object[] array, String message) {
        /*if (ObjectUtils.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }*/
    }

    public static void hasLength( String text, String message) {
        /*if (!StringUtils.hasLength(text)) {
            throw new IllegalArgumentException(message);
        }*/
    }
}
