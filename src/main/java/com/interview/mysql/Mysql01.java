package com.interview.mysql;

/**
 * @Author suosong
 * @Date 2018/8/15
 *

 mysql在调优之前，第一部就是要学会观察mysql的状态，根据状态来判断怎么去调优

Awk脚本

Awk是一个简便的直译式的文本处理工具.
擅长处理--多行多列的数据

处理过程:
While(还有下一行) {
    1:读取下一行,并把下一行赋给$0,各列赋给$1,$2...$N变量
    2: 用指定的命令来处理该行
}

如何处理1行数据?
答: 分2部分,   pattern (条件)  + action(处理动作)

第1个简单awk脚本
awk  ‘{printf(“%s\n” , $1)}’ xx.txt  // 把xx.txt的每一行的第一列进行输出

第2个简单awk脚本 统计mysql服务器信息
mysqladmin -uroot ext|awk  'Queries/{q=$4}/Threads_connected/{c=$4}/Threads_running/{r=$4}END{printf("%d %d %d\n",q,c,r)}'


 *
 */
public class Mysql01 {
}
