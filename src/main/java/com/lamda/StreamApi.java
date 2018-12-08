package com.lamda;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author suosong
 * @Date 2018/10/28
 java8中，对数组跟集合增加了流的操作
 1，集合是一个数据的概念，流是一个计算的概念
 2，流并不会对数据源产生改变，流走完后会有一个新的集合
 3，stream自己不会存储元素
 4，stream不会改变源对象，相反，会返回一个持有结果的新stream
 5，stream是延迟执行的，意思就是等到需要结果的时候才执行
 */
public class StreamApi {

    List<Integer> list ;
    @Before
    public void setup(){
        list = Arrays.asList(1,2,3,4,5,6,7,8,9,9);
    }
    /**
      filter: 接收一个lamda，从流中选择符合条件的某些元素,使用的是Prodicate接口
     */
    @Test
    public void test_筛选(){
        Stream<Integer> stream = list.stream();
        stream = stream.filter((x)-> x>5);
        //使用的是Consumer接口，只有执行此语句时，延迟的流才开始流动
        stream.forEach(System.out::println);
    }


    @Test
    public void test_限制(){
        list.stream().limit(3).forEach(System.out::println);
    }

    @Test
    public void test_跳过(){
        list.stream().skip(1).forEach(System.out::println);
    }

    /**
     * 去重是通过对比hashCode 跟equals
     */
    @Test
    public void test_去重(){
        list.stream().distinct().forEach(System.out::println);
    }


    /**
     * 传入一个lamda表达式，是Function
     */
    @Test
    public void test_函数处理(){
        list.stream().map(e-> 0-e).forEach(System.out::println);
    }

    /**
     * 排序
     * 自然排序
     * 定制排序
     *
     */








    @Test
    public void test(){
        A a = new A();
        System.out.println(JSON.toJSONString(a));
    }

    class A{
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

}
