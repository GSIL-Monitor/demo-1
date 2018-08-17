package com.lunzi.spring.core.io;

import com.lunzi.spring.utils.Assert;
import com.lunzi.spring.utils.ClassUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 不知道这里的ClassLoader是否有用
 * @Author suosong
 * @Date 2018/7/11
 */
public class FileSystemResource implements Resource {

    ClassLoader classLoader;
    String path;

    public FileSystemResource(ClassLoader classLoader, String path) {
        Assert.notNull(path,"路径不能为空");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    public FileSystemResource(String path) {
        this(null, path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(path);
    }

    @Override
    public String getDescription() {
        return null;
    }
}
