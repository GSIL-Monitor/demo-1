package com.example.utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * @Author suosong
 * @Date 2018/8/7
 */
public abstract class FileUtil {
    /**
     * 把类路径下的文件转为绝对路径文件
     * 这个是取的真正类路径下的文件，如果项目没有打包运行，就是取的target/classes下的文件。
     *
     * @param fileName
     * @return
     */
    @Deprecated//如果已经打成了jar包，返回的地址包含jar文件，是不能使用的
    public static String getAbsPathFromClassPath(String fileName) {


        URL url = FileUtil.class.getClassLoader().getResource(fileName);
        if (url != null) {
            return url.getFile();
        }
        //url.getPath()

        throw new RuntimeException("类路径下此文件不存在 " + fileName);


    }

    /**
     * 获得相对于项目地址的 相对路径
     * 主要用于 new File 的参数 在jar包中执行的程序，必须这么做。因为绝对路径中会包含.jar
     * @param classPathFileName 类路径下的文件名
     * @return
     */
    @Deprecated
    public static String getProjectPath2ClassPath(String classPathFileName){
        URL url = FileUtil.class.getClassLoader().getResource(classPathFileName);
        if (url != null) {
            String path = url.getFile();
            path = path.replace(new File("").getAbsolutePath(),"");
            if(path.contains("/")){
                path = path.substring(1);
            }else if(path.contains("\\\\")){//windows
                path = path.substring(2);
            }
            return path;
        }
        //url.getPath()

        throw new RuntimeException("类路径下此文件不存在 " + classPathFileName);
    }

    /**
     * 获得项目路径
     * 如果用IDE起程序，获得的是项目路径
     * 如果用java -jar xxx.jar的方式启动程序。获得的是java命令所在的路径
     * @return
     */
    public static String getProjectPath(){
        return new File("").getAbsolutePath();
    }

    public static void main(String[] args) {
        //System.out.println(getAbsPathFromClassPath("log4j.xml"));

        System.out.println(getProjectPath());





    }
}
