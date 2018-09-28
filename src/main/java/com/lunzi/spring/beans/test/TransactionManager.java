package com.lunzi.spring.beans.test;

/**
 * 为aop做准备
 * 作为aop 的aspect (切面)
 * @Author suosong
 * @Date 2018/8/24
 */
public class TransactionManager {

    public void start(){
        System.out.println("start...tx...");
    }

    public void commit(){
        System.out.println("commit..tx..");
    }

    public void rollback(){
        System.out.println("rollback...tx..");
    }
}
