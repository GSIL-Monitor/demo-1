package com.spring.lifecycle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @Author suosong
 * @Date 2018/9/28
 */
public class LifecycleTest01 {

    /**
     * 测试生命周期中实例化跟销毁两个方法
     */
    @Test
    public void test_init_destroy() throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifecycleConfig.class);
        applicationContext.close();
        TimeUnit.SECONDS.sleep(2);
    }

    /**
     * bean后置处理器
     */
    @Test
    public void test_beanPostProcessor(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(LifecycleConfig.class);

    }

}
