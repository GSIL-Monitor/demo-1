package com.example.log4j;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * log4j里面有的初始化不受spring容器的控制。
 * 要想把log4j.xml中的${} 给填充，必须把变量放入System.property中
 * 需要在spring容器初始化完成后，让log4j重新读取xml文件
 *
 * @Author suosong
 * @Date 2018/8/7
 */
@Component
public class Log4jListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 容器启动完成之后load
        if (event instanceof ContextRefreshedEvent) {
            if (((ContextRefreshedEvent) event).getApplicationContext().getParent() == null) {
                reloadLog4j();
            }
        }
    }

    @Value("${log4j.path}")
    String log4jPath;

    /**
     * 重新读取xml
     */


    public void reloadLog4j() {

        System.out.println("===========填充log4j.xml");


        System.setProperty("log4j.path", log4jPath);
        //从类路径下取
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("log4j_back.xml");
        /*String projectPath = FileUtil.getProjectPath();
        System.out.println("projectPath = " + projectPath);*/
        try {

            //将log4j拷贝至项目路径或者java命名所在目录下。需要准确了解new File()相对路径 相对的是哪个路径
            //将拷贝的文件删除
            File copyFile = new File("log4j_back.xml");
            if (copyFile.exists()) {
                copyFile.delete();
            }
            FileUtils.copyInputStreamToFile(inputStream, new File("log4j_back.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }

        DOMConfigurator.configure("log4j_back.xml");
        //DOMConfigurator.
    }



}
