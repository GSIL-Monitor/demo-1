package com.spring.tx;

import com.tx.Method01;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author suosong
 * @Date 2018/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TxText {

    @Autowired
    Method01 method01;
    /**
     * 测试@Tranctional放的位置
     */
    @Test
    public void test(){
        method01.updateTest02();
    }
}
