package com.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {
    int i = 0;

    @GetMapping("/test")
    public Map<String, String> getMap(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        if (i++ % 2 == 0) {
            map.put("hello", "spring boot");
        } else {
            map.put("name", "呱嗒");
            response.setStatus(304);
            //return null;
        }


        //for(String key : request.getHeaderNames())
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = headers.nextElement();
            System.out.println(key + "  " + request.getHeader(key));
        }
        System.out.println("=================================================================================");

        response.setHeader("Cache-Control", "max-age=1000");


        return map;
    }

    @GetMapping("/hello/{name}")
    @ApiOperation(value="获得用户名", notes="传入用户名，返回打招呼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String"),
    })
    public String getName(@PathVariable("name") String name) {
        return "hello:" + name;
    }

   /* @GetMapping("/hello/question")
    @ApiOperation(value="获得用户名", notes="传入用户名，返回打招呼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String"),
    })
    public String getName(@PathVariable("name") String name) {
        return "hello:" + name;
    }*/


}
