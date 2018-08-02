package com.example.other.test01;

import org.springframework.stereotype.Component;

@Component
public class Chinese implements Person{

    private Account account;

    private String userName;

    @Override
    public void sayHello() {
        System.out.println("中国");
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Account getAccount() {
        return account;
    }

    public String getUserName() {
        return userName;
    }
}
