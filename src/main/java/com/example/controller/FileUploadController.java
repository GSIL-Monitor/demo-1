package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @Author suosong
 * @Date 2018/8/14
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @PostMapping("/file/v1")
    public Object upload(HttpServletRequest request) {
        return "";
    }

    @PostMapping("/file/v2")
    public Object upload2(MultipartFile key) {
        File dir = new File("/Users/peter/suosong/tempUpload");
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = key.getOriginalFilename();
        //取后缀
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            if (index < fileName.length() - 1) {
                fileName = fileName.substring(index);
            }
        }
        File file = new File(dir, System.currentTimeMillis() + fileName);
        try {
            key.transferTo(file);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


}
