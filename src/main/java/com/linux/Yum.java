package com.linux;

/**
 * @Author suosong
 * @Date 2018/8/18


================================================================
练习：
    1，查看机器是否安装了mysql



================================================================

 yum list                //列举出所有的yum软件，包括已经安装跟未安装的，必须联网才能使用，由于太多，一般是配合管道一起使用
 yum list installed      //只列举已经安装的软件

软件名称                                    软件版本                    软件来源
mingw64-gcc-objc++.x86_64                 4.9.3-1.el7                  epel


 yum search mysql        //搜索包含mysql关键词的安装包


 yum -y install mysql    //安装mysql   -y是可以省略的，y是yes的时候，就是把确认操作都省了


 yum [-y] update [包名]        //更新软件 建议不写-y  如果不写包名的话，会把可以更新的软件全部列出来


 yum  [-y] remove 包名      //卸载软件

















 */
public class Yum {
}
