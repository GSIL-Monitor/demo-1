package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author suosong
 * @Date 2018/9/17
 */
@RestController
public class Nginx01 {
    @Value("${server.port}")
    Integer port;
    @GetMapping("/getServerIp")
    public String getServerConnectInfo(){
        try {
            String ip = InetAddress.getLocalHost().getHostAddress() + ":"+port;
            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "error";
    }

}
