package com.lunzi.spring.core.io.support;

import com.lunzi.spring.core.io.FileSystemResource;
import com.lunzi.spring.core.io.Resource;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责扫描包，返回Resource接口数组
 *
 * @Author suosong
 * @Date 2018/8/8
 */
public class PackageResourceLoader {


    /**
     * 自己实现的，跟spring不一样
     *
     * @param packageName
     * @return
     */
    public Resource[] getResources(String packageName) {

        String path = ClassUtils.convertClassNameToResourcePath(packageName);

        URL url = ClassUtils.getDefaultClassLoader().getResource(path);

        if (url == null) return null;

        String absPath = url.getFile();

        List<Resource> resourceList = new ArrayList<>();

        this.getResources(new File(absPath), resourceList);

        Resource[] resources = new Resource[resourceList.size()];

        resourceList.toArray(resources);

        return resources;
    }

    /**
     * 缺点是当打成jar包的时候，运行是有问题的。
     *
     * @param file
     * @param resourceList
     */
    private void getResources(File file, List<Resource> resourceList) {
        if (file.exists()) {
            if (file.isFile()) {
                if (file.canRead()) {
                    resourceList.add(new FileSystemResource(file.getAbsolutePath()));
                }
            } else {
                //目录
                for (File childFile : file.listFiles()) {
                    getResources(childFile, resourceList);
                }
            }
        }
    }

}
