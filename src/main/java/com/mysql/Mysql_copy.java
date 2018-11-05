package com.mysql;

/**
 * @Author suosong
 * @Date 2018/11/4
 讲mysql主从复制

 原理
    mysql的change信息会保存为binary log (bin log),这个记录日志的过程叫做二进制日志事件
    slave 会读取binary log 并且放在relay log (中继日志)
    根据relay log 的事件 去同步salve
    mysql的复制是异步的且串行化的（单线程）
    master 不会无缘无故的让其他mysql去复制自己的日志，需要一个授权账户跟授权密码。


 复制规则
    每个slave 只能有一个master
    master可以有多个slave
    mysql版本最好一致，不然可能会出现不可预知的错误


 mysql-master配置 /etc/mysql/my.cnf
    1，开启bin-log日志
        [mysqld]
            log-bin=mysql-bin        bin log的前缀




 */
public class Mysql_copy {
}
