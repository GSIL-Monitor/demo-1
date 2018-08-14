package com.example.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/8/14
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {
    @PostMapping("/file/v1")
    public Object upload(HttpServletRequest request) {
        //只有request中包含Multipart，才能正常上传
        /*boolean containsMultipart = ServletFileUpload.isMultipartContent(request);
        if (!containsMultipart) return "error , not exist multipart";*/
        //设置了缓存区大小，如果文件超过缓存区，那么就需要用到临时存储区
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(10 * 1024 * 1024, new File("/Users/peter/suosong/tempUpload"));
        List<FileItem> fileItems;
        try {
            fileItems = new ServletFileUpload(diskFileItemFactory).parseRequest(request);
            System.out.println("size=" + fileItems.size());
            for (FileItem fileItem : fileItems) {
                File file = new File("/Users/peter/suosong/123.jpg");

                //fileItem.write(file);不好使
                System.out.println("fileName=" + fileItem.getFieldName());
                byte[] bytes = fileItem.get();
                FileUtils.writeByteArrayToFile(file, bytes);
            }
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    @PostMapping("/file/v2")
    public Object upload2(MultipartFile key) {
        File dir = new File("/Users/peter/suosong/tempUpload");
        if (!dir.exists()) dir.mkdir();
        String fileName = key.getOriginalFilename();
        //取后缀
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            if (index < fileName.length() - 1) {
                fileName = fileName.substring(index );
            }
        }
        File file = new File(dir, System.currentTimeMillis()  + fileName);
        try {
            key.transferTo(file);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }


}
