package com.example.other.test01;

/**
 * @Author suosong
 * @Date 2018/8/5
 */
public class Cat {
    Account account;
    String name;
    int age;

    public Cat(Account account, String name, int age) {
        this.account = account;
        this.name = name;
        this.age = age;
    }

   /* public Cat() {
    }*/

    public Account getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }
}
