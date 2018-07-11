package com.example.lunzi.spring.core.io;

import com.example.lunzi.spring.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author suosong
 * @Date 2018/7/11
 */
public class ClassPathResource implements Resource {

    ClassLoader classLoader;
    String path;

    public ClassPathResource(ClassLoader classLoader, String path) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    public ClassPathResource(String path) {
        this(null, path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (path == null) return null;

        return this.classLoader.getResourceAsStream(path);

    }

    @Override
    public String getDescription() {
        return null;
    }
}
