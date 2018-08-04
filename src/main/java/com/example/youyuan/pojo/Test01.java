package com.example.youyuan.pojo;

import java.util.Date;

/**
 * @Author suosong
 * @Date 2018/8/3
 */
public class Test01 {
    long id;
    String name;
    int age;
    Date addTime;
    Date updateTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Test01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
