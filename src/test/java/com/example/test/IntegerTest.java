package com.example.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/7/22
 */
public class IntegerTest {

    @Test
    public void test(){
        Integer i1 = new Integer(4);
        Integer i2 = new Integer(4);
        System.out.println(i1 == i2);

        int i = 444;
        System.out.println(i == i2);

    }

    @Test
    public void test2(){
        Integer i1 = Integer.valueOf(56);
        Integer i2 = Integer.valueOf(56);
        Assert.assertTrue(i1 == i2);
    }

    @Test
    public void test3(){

        Integer i1 = 129;
        Integer i2 = 129;
        Assert.assertNotSame(i1,i2);

    }

}
