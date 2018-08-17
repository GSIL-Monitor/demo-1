package com.lunzi.spring.context.support;

import com.lunzi.spring.core.io.FileSystemResource;
import com.lunzi.spring.core.io.Resource;

/**
 * @Author suosong
 * @Date 2018/7/11
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext{

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(this.getBeanClassLoader(),path);
    }

}
