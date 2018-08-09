package com.example.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author suosong
 * @Date 2018/8/7
 */
@ControllerAdvice
public class TestControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handleError(Exception e){
        Map<String ,Object> map = new HashMap<>();
        map.put("e",e.getMessage());
        return map;
    }
}
