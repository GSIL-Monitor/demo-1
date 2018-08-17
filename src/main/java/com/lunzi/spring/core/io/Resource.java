package com.lunzi.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 鉴于ClassPathXmlApplicationContext 与 FileSystemApplicationContext资源加载的方式不同，所以抽取出了Resource接口
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
    String getDescription();
}
