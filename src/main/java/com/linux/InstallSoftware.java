package com.linux;

/**
 * @Author suosong
 * @Date 2018/8/18

 源码包安装
    开源软件才有源码包，如果有足够的能力，完全可以去修改源代码。
    安装包基本都有配置（configure），编译(make),安装（make install）的过程
    解压后，一般会有个可执行文件configure
    ./configure --prefix=/opt/app/nginx          //指定安装目录，如果指定的目录不存在，会自动创建
    make                                         //编译
    make install                                 //安装

    make && make install                         //将编译跟安装一块做

 */
public class InstallSoftware {
}
