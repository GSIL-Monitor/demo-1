package com.spring.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author suosong
 * @Date 2018/8/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleTest {

    @Resource
    ScheduleService scheduleService;

    /**
     * 测试spring 的 @Async注解
     */
    @Test
    public void test() throws InterruptedException {
        scheduleService.test01();
        System.out.println("======end=======");
        TimeUnit.SECONDS.sleep(10);
    }

    /**
     * 测试异步方法被同类方法直接调用
     */
    @Test
    public void test02() throws InterruptedException {
        scheduleService.test03();
        System.out.println("======end=======");
        TimeUnit.SECONDS.sleep(10);
    }
}
