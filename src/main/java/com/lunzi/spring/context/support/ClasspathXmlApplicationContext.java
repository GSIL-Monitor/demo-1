package com.lunzi.spring.context.support;

import com.lunzi.spring.core.io.ClassPathResource;
import com.lunzi.spring.core.io.Resource;

/**
 * @Author suosong
 * @Date 2018/7/11
 */
public class ClasspathXmlApplicationContext extends AbstractApplicationContext{

    public ClasspathXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        //支持外界传入的classLoader.
        return new ClassPathResource(this.getBeanClassLoader(),path);
    }


}
