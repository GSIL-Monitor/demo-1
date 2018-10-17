package com.spring.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author suosong
 * @Date 2018/10/5
 */
public class LogTest01 {
    @Test
    public void test(){
        Logger logger = LoggerFactory.getLogger(getClass());

    }
}
