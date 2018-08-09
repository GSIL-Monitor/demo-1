package com.example.lunzi.spring.testconfig;

import com.example.lunzi.spring.core.io.Resource;
import com.example.lunzi.spring.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author suosong
 * @Date 2018/8/9
 */
public class PackageResourceLoaderTest {

    @Test
    public void test(){

        PackageResourceLoader packageResourceLoader = new PackageResourceLoader();

        Resource[] resources = packageResourceLoader.getResources("com.example.lunzi.spring.testconfig");

        Assert.assertSame(resources.length,2);

    }
}
