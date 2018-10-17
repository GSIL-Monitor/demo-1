package com.spring.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author suosong
 * @Date 2018/9/28
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.lifecycle"})
public class LifecycleConfig {
    @Bean
    public Lifecycle01 lifecycle01(){
        return new Lifecycle01();
    }
}
