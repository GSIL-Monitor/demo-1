package com.example.other.test01;

/**
 * @Author suosong
 * @Date 2018/8/5
 */
public class Order {

    private int age;

    public Order(int age) {
        this.age = age;
    }
    private Inner inner = new Inner(age);

    static class Inner{
        public Inner(int num) {
            System.out.println(num);
        }
    }

    public static void main(String[] args) {
        new Order(12);
    }
}
