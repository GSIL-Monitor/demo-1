package com.example.service;

import com.DemoApplication;
import com.example.other.test01.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DemoApplication.class})
public class Test01 {
    @Resource
    Account account;

    @Test
    public void test01(){
        account.say();
    }
}
