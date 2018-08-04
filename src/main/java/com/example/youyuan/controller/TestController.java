package com.example.youyuan.controller;

import com.example.youyuan.dto.ResultJson;
import com.example.youyuan.dto.ResultType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("weefiei45334erf")
public class TestController {
    @GetMapping("/youyuan/test")
    public ResultJson get(){
        Map<String,String> map = new HashMap<>();
        map.put("name","张三");
        map.put("address","北京");
        return new ResultJson(ResultType.SUCCESS,"syrni");
    }

    @GetMapping("/")
    public String index(){
        return "曹会雅，小笨蛋";
    }



}
