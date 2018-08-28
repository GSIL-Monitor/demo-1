package com.spring.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author suosong
 * @Date 2018/8/28

 @Async 注解
 这个注解在springboot 要起作用，必须满足以下两个条件
    1，启动类有@EnableAsync注解
    2，异步方法必须被不同类直接调用
 具体测试方法，在test里面有

 */
@Service
public class ScheduleService {
    @Async
    public void test01() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(" schedule .......");
    }


    @Async
    public void test02(){
        System.out.println("我是异步方法，但是被同类方法调用了。。。。。。");
    }

    public void test03() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        test02();
    }

}
