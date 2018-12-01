package com.docker;

/**
 * @Author suosong
 * @Date 2018/11/2

 docker容器与虚拟机比对
    传统虚拟机技术是虚拟出一套硬件后（很重要），在其上运行一个完整操作系统，在该系统上再运行所需应用
    docker容器内的应用进程直接运行于宿主的内核，容器内没有自己的内核，而且也没有进行硬件虚拟。因此容器要比传统虚拟机更为轻便（只有软件，没有硬件）
    每个容器之间互相隔离，每个容器有自己的文件系统，容器之间进程不会相互影响，能区分计算资源。
    可以把容器看成是一个简易版的linux环境（包括root用户权限，进程空间，用户空间和网络空间等）和运行在其中的应用程序


 docker出现的比较晚，2014年出现，2017年才大火。所以要安装docker，要求centOS在6.5（64位）版本以上。
 查看centos版本 cat /etc/reahat-release（centos7.x 不支持此命令）

 安装docker-ce（社区版，免费）
 centos7 安装，官网有教程
    1，sudo yum install -y yum-utils \
        device-mapper-persistent-data \
        lvm2
    2，sudo yum-config-manager \
        --add-repo \
        http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

    3，sudo yum makecache fast
    4，sudo yum install -y docker-ce
    5，sudo systemctl start docker

 配置使用阿里云的镜像加速器，避免国外的docker仓库网络缓慢问题
    针对Docker客户端版本大于 1.10.0 的用户

    您可以通过修改daemon配置文件/etc/docker/daemon.json来使用加速器
    写下面
    {
    "registry-mirrors": ["https://0rqfqqwi.mirror.aliyuncs.com"]
    }

    sudo systemctl daemon-reload
    sudo systemctl restart docker


 docker 命令查询
    docker --help

 docker 常用命令
    docker images  列出本地的docker镜像
    docker search  类似于在maven仓库中搜jar包。
    docker pull tomcat == docker pull tomcat:latest
    docker ps   列出当前所有正在运行的容器

    docker run [option] image名字 [command] [args]   从image创建一个容器实例，并启动它, command是容器启动后再容器中运行的命令
        -d 守护进程的方式启动（运行在后台,不会进入容器交互窗口）
        -it 进入容器交互界面
        --name 给容器起个名字，如果不起名字，就有默认名字

    docker start [-i] 容器id    启动容器
    docker restart 容器id       重启容器
    docker stop 容器id          停止容器
    docker rm 容器id            删除已经停止的容器
    docker top 容器id           查看容器内的进程
    docker exec -it 容器id  bin/bash    进入容器交互界面  退出交互界面用exit，容器不会停止 如果用attach ，退出后容器会停止。这个命令的意思是在容器中再开一个进程，所以退出这个进程后，容器不会停止，指的是/bin/bash这个进程
    docker cp 容器id: 源文件路径 目的路径   从容器内拷贝文件到宿主机
    docker top  容器名   查看运行中容器的进程
    docker port 容器名   端口映射情况
    docker inspect 容器名  查看容器的基本信息，比如ip。因为在容器里，很多命令都不好用，比如ifconfig，需要注意的是，容器重新启动后，ip会改变的


 docker的设计机制
    每一个容器的后台进程都有一个前端交互，如果前端交互关闭了，后台进程如果无事可做，也会自动关闭
    容器运行的如果不是一些一直挂起的命令，比如tail,top等，那么容器就会很快自动退出（容器觉得自己没有事情可做了）
    这个是docker设计机制的问题，需要说明的是，前端窗口除非自己手动关闭（exit命令）否则是不会关闭的。

 docker 启动tomcat
    docker run -d -p80:8080 tomcat     80是宿主机端口 8080是容器端口
 docker 启动mysql
    docker run --name mysql -p 8901:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql:5.5
    说明，我的阿里云的内存为1g，跑不了5.6版本的mysql。开启容器后，很快就自动退出。换成5.5版本后，可以跑起来


 docker 基于容器打包新镜像
    docker commit -a '作者名' -m '注释' 容器id  suosong/tomcat:2.4.5

 容器数据卷


 容器与宿主机之间数据的共享
    docker run -v 宿主机路径:容器路径 容器id    这样，就可以在文件夹内同步数据


 网络通信


 */
public class Docker01 {
    public static void main(String[] args) {
        String sql = "'name\'s'";
        System.out.println(sql);
    }
}
