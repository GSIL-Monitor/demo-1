package com.lunzi.spring.beans.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author suosong
 * @Date 2018/6/27
 */
public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public void save(){
        System.out.println("save......");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) throws ParseException {

        ThreadLocal<SimpleDateFormat> sdfThreadLocal = new ThreadLocal<>();
        SimpleDateFormat sdf = sdfThreadLocal.get();
        if(sdf == null){
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdfThreadLocal.set(sdf);
        }
        String str = "2019-01-01 00:00:01.0";
        Date date = sdf.parse(str);
        System.out.println(date);

        str = "\u00A078";
        System.out.println(str.replace("\u00A0","-"));
    }

}
