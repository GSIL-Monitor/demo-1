package com.lamda;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * lamda表符号 ->
 * 左侧是参数列表
 * 右侧是执行体
 * 我自己感觉，就是一个函数，有函数参数跟函数体。不同的是，没有函数名.在需要传入接口的地方，可以用lamda表达式替代。等于是变相的可以传入函数
 * lamda表达式是一个语法糖，本质还是一个匿名内部类
 * @Author suosong
 * @Date 2018/10/16
 */
public class LamdaTest01 {
    @Test
    public void test01() throws InterruptedException {
        Runnable runnable =()->{
            System.out.println(Thread.currentThread().getName());
        };
    }
    @Test
    public void test02() throws InterruptedException {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * 方法引用
     * 对象::方法  代替lamda表达式
     * 前提是方法的参数跟返回值类型跟接口方法是一样的
     */
    @Test
    public void test03(){
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello lamda");
    }



}
