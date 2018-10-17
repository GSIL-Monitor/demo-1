package com.spring.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Author suosong
 * @Date 2018/9/28
 *
 */
public class Lifecycle01 implements InitializingBean,DisposableBean{

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("lifecycle01 init");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("lifecycle01 dead");
    }
}
