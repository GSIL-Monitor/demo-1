package com.lamda;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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
     * 要用到lamda表达式，需要事先定义接口，这个就有些不方便。java为了让用户使用方便，内置了几个函数式接口
     * java 内置函数式接口
     * Consumer<T>   消费性接口，有去无回，所以没有返回值
     *     void accept(T t)
     * Suplier<T>    供给性接口，用来返回数据
     *     T get()
     * Function<T,R>  函数性接口 T为参数类型，R为返回值类型
     *     R apply(T t)
     * Predicate<T>  断言性接口
     *     boolean test(T t)
     *
     */
    @Test
    public void test_Consumer(){
        happy(100,(i)-> System.out.println("我喜欢吃肯德基，每次消费："+i+" 元"));
    }
    public void happy(int money,Consumer<Integer> consumer){
        consumer.accept(money);
    }
    @Test
    public void test_Suplier(){
        List<Integer> list = getNumList(()->new Random().nextInt());
        System.out.println(list);
    }
    public List<Integer> getNumList(Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i = 0 ; i< 5 ; i++){
            list.add(supplier.get());
        }
        return list;
    }
    @Test
    public void test_Function(){
        String result = strHandler("hello",(str)->str.toUpperCase());
        System.out.println(result);
        result = strHandler("hello wold",(str)-> str.substring(0,5));
        System.out.println(result);
    }
    public String strHandler(String str, Function<String,String> function){
        return function.apply(str);
    }
    @Test
    public void test_Predicate(){
        List<String> list = new ArrayList(Arrays.asList("abc","ab","c"));
        strFilter(list,(str)->str.startsWith("a"));
        System.out.println(list);
    }
    public void strFilter(List<String> list, Predicate<String> predicate){
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String str = iterator.next();
            if(!predicate.test(str)){
                iterator.remove();
            }
        }
    }






    /**
     * 方法引用
     * 如果lamba体的内容有方法已经实现了，那我们可以使用方法引用
     * 可以理解为，方法引用是lamda表达式的另外一种表现形式
     * 前提是方法的参数跟返回值类型跟接口方法是一样的
     * 主要有三种语法格式：
     *
     方法1：
      对象::实例方法名
     方法2：
      类::类方法名
     方法3：
      类：实例方法名

     */
    @Test
    public void test_方法引用01(){
        happy(1000,System.out::println);
    }

    @Test
    public void test_方法引用02(){
        Supplier<Double> supplier = Math::random;
        System.out.println(supplier.get());
    }




}
