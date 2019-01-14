package com.lunzi.spring.beans.test;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 为aop做准备
 * 作为aop 的aspect (切面)
 *
 * @Author suosong
 * @Date 2018/8/24
 */
public class TransactionManager {

    public void start() {
        System.out.println("start...tx...");
    }

    public void commit() {
        System.out.println("commit..tx..");
    }

    public void rollback() {
        System.out.println("rollback...tx..");
    }

    public static LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder().maximumSize(1)
            .expireAfterWrite(1, TimeUnit.SECONDS)
            .removalListener(new RemovalListener() {
                @Override
                public void onRemoval(RemovalNotification rn) {
                    System.out.println(rn.getKey() + "被移除");
                }
            })
            .build(new CacheLoader<String,String>() {
                @Override
                public String load(String key) throws Exception {
                    System.out.println("缓存中没有了，开始查库===");
                    return "tom";//缓存中没有，直接返回null
                }

            });


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        cahceBuilder.put("name","jerry");
        cahceBuilder.put("age","30");
        String res = cahceBuilder.get("name");
        System.out.println(res);
        Thread.sleep(3000);
        res = cahceBuilder.get("name");
        System.out.println(res);
        res = cahceBuilder.get("name");
        System.out.println(res);
        res = cahceBuilder.get("name");
        System.out.println(res);


        //cahceBuilder.get("name");


    }



}
