package com.example.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 令牌桶限流工具
 * @Author suosong
 * @Date 2018/7/6
 */
public class RateLimiterTest {
    @Test
    public void test() throws InterruptedException {
        //qps 为5
        RateLimiter rateLimiter = RateLimiter.create(5);

        while (true){
            //注意，并不是真的等待1秒钟，这个方法是立即返回的。只是判断或者预测1秒钟内能不能得到一个令牌
            //中间的动态过程经历了数学运算
            if(rateLimiter.tryAcquire(1000, TimeUnit.MILLISECONDS)){
                System.out.println("通过了");
            }else {
                System.out.println("被限流了");
                break;
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }

    }
}
