package com.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author suosong
 * @Date 2018/8/30
 *
 学习了一个新词，泛型 generic 之前经常不能把这两个词给联系起来，泛型这个词语就让人很难理解。generic反倒是比较容易理解
 */
public class Generic01<T> {
    private Class<T> clazz;
    public Generic01() {
        this.clazz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public static void main(String[] args) {
        Generic02 generic02 = new Generic02();
        System.out.println(generic02.getClazz().getSimpleName());
    }


}

class Generic02 extends Generic01<String>{

}