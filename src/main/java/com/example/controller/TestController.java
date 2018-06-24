package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    @GetMapping("/test")
    public Map<String,String> getMap(HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        map.put("hello","spring boot");



        //for(String key : request.getHeaderNames())
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            String key = headers.nextElement();
            System.out.println(key+"  "+ request.getHeader(key));
        }
        System.out.println("=================================================================================");



        return map;
    }


}
