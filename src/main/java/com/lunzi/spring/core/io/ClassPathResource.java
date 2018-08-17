package com.lunzi.spring.core.io;



import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

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
        Assert.notNull(path,"路径不能为空");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    public ClassPathResource(String path) {
        this(null, path);
    }

    @Override
    public InputStream getInputStream() throws IOException {

        return this.classLoader.getResourceAsStream(path);

    }

    @Override
    public String getDescription() {
        return null;
    }
}
