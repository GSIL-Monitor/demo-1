package com.lunzi.spring.beans.test;

/**
 * @Author suosong
 * @Date 2018/8/24
 */
public class TransactionManager {

    public void start(){
        System.out.println("start...tx...");
    }

    public void execute(){
        System.out.println("commit..tx..");
    }

    public void rollback(){
        System.out.println("rollback...tx..");
    }
}
