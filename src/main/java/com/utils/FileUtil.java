package com.utils;


import java.io.File;
import java.net.URL;
import java.util.List;

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
     *
     * @param classPathFileName 类路径下的文件名
     * @return
     */
    @Deprecated
    public static String getProjectPath2ClassPath(String classPathFileName) {
        URL url = FileUtil.class.getClassLoader().getResource(classPathFileName);
        if (url != null) {
            String path = url.getFile();
            path = path.replace(new File("").getAbsolutePath(), "");
            if (path.contains("/")) {
                path = path.substring(1);
            } else if (path.contains("\\\\")) {//windows
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
     *
     * @return
     */
    @Deprecated
    public static String getProjectPath() {
        return new File("").getAbsolutePath();
    }

    /**
     * 确保文件夹存在
     *
     * @param dir
     */
    public static void confirmDirExist(File dir) {
        if (dir == null) return;
        if (!dir.exists()) {
            confirmDirExist(dir.getParentFile());
            dir.mkdir();
        } else {
            //只有文件真实存在了，才能判断是否可读写
            if (!dir.canWrite()) throw new RuntimeException("this directory can not write  ，" + dir);
        }
    }

    /**
     * 取文件的后缀名
     *
     * @param fileName
     * @return
     */
    public static String getFileSuffix(String fileName) {
        String suffix = null;
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            if (index < fileName.length() - 1) {
                suffix = fileName.substring(index + 1);
            }
        }
        return suffix;
    }

    /**
     * 统计目录的大小,只计算最小目录，比如 /usr/local    /usr/local/hadoop ，如果后者在范围内，前者就不会出现在统计结果中
     *
     * @param path
     * @param minSize 最小，单位字节
     * @param maxSize 最大，单位字节
     * @return
     *//*
    public static boolean statisticDiskSize(String path, long minSize, long maxSize) {
        File file = new File(path);
        if (!file.exists()) throw new RuntimeException("该路径不存在");
        if (!file.isFile()) {
            for (File childDir : file.listFiles()) {
                boolean isReachMax = statisticDiskSize(childDir.getPath(), maxSize, minSize);
                if(isReachMax)
            }
        }
        long dirSize = statisticDirectorySize(file);
        if (dirSize >= minSize && dirSize <= maxSize) {
            System.out.println("path=" + path + " size=" + dirSize / 1024 / 1024 + "");
            return true;//已经达到
        }
    }
*/

    /**
     * 查看目录的大小
     *
     * @param dir
     * @return
     */
    public static long statisticDirectorySize(File dir, long minSize) {
        if (!dir.exists()) return 0;
        if (dir.isFile()) return dir.length();
        long sum = 0;
        for (File childDir : dir.listFiles()) {
            sum += statisticDirectorySize(childDir, minSize);
        }

        if (sum >= minSize) {
            System.out.println("path=" + dir.getPath() + "     " + sum / 1024 / 1024/1024 + "G");
        }
        return sum;
    }


    public static void main(String[] args) {


        //System.out.println(getFileSuffix("/Users/peters.p"));
        File file = new File("/Users/peter/suosong");
        System.out.println(file.getTotalSpace());
        System.out.println(file.getFreeSpace());
        System.out.println(file.getUsableSpace());

        statisticDirectorySize(new File("/Users/peter/suosong"), 1024L * 1024 * 1024 );

        //System.out.println(1024L*1024*1024*2);
    }
}
