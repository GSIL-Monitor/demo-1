package com.example.other;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author suosong
 * @Date 2018/9/14
 */
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("ip===="+ip);
    }
}
