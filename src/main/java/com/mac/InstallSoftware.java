package com.mac;

/**
 * @Author suosong
 * @Date 2018/8/27

安装软件，提示下面
    「xxx.app已损坏,打不开.你应该将它移到废纸篓」，并非你安装的软件已损坏，而是Mac系统的安全设置问题，因为这些应用都是破解或者汉化的,那么解决方法就是临时改变Mac系统安全设置。

    出现这个问题的解决方法：
    修改系统配置：系统偏好设置... -> 安全性与隐私。修改为任何来源
    如果没有这个选项的话（macOS Sierra 10.12）,打开终端，
    执行
    sudo spctl --master-disable
    即可。


 */
public class InstallSoftware {
}
