package com.linux;

/**
 * 讲了awk的基本用法
 * awk的语法非常灵活，同样的操作，简写跟全写差别很大。
 * 作为一个java程序员，掌握其中的一种写法就ok，最后面有几个例子，那几个例子是按照程序员风格写的。比如不会省去if关键字
 * if只能写在{}里面
 * @Author suosong
 * @Date 2018/8/16
 *








//////////////////////////////////////////////////////////////////////////////////////
此处为练习，用来温故知新的。如果练习不会做了，就看下面的讲解
mysql 的 show status命令，得到的数据格式如下：
| Queries                                       | 423496                                           |
| Threads_connected                             | 21                                               |
| Threads_running                               | 1                                                |

写出每个变量的次数



//////////////////////////////////////////////////////////////////////////////////////











索松总结的awk的总体规则是：  '[条件]  {执行命令}'  虽然不是全部，但是这是我的风格。这个命令可以是一条或者多条命令，也可以是代码块，比如if else代码块，语法跟java一样
举个例子：
awk '{if($2>20){print "20"}else {print "-20"}}' awk.txt



awk处理过程: 依次对每一行进行处理，然后输出
awk命令形式:
awk [-F|-f|-v] ‘BEGIN{} //{command1; command2} END{}’ file
[-F|-f|-v]   大参数，-F指定分隔符，-f调用脚本，-v定义变量 var=value

'  '    引用代码块
BEGIN   初始化代码块，在处理数据之前，初始化代码，主要是引用全局变量，设置FS分隔符
//      匹配代码块，可以是字符串或正则表达式
{}      命令代码块，包含一条或多条命令
；       多条命令使用分号分隔
END      结尾代码块，在对处理完所有数据之后再执行的代码块，主要是进行最终计算或输出结尾摘要信息


特殊要点:
$0           表示整个当前行
$1           每行第一个字段
NF           字段数量变量（内部变量）
NR           每行的记录号，多文件记录递增（可以理解为每行的id，跟hadoop是一样的）
FNR          与NR类似，不过多文件记录不递增，每个文件都从1开始
\t           制表符
\n           换行符
FS           BEGIN时定义分隔符
RS           输入的记录分隔符， 默认为换行符(即文本是按一行一行输入)（一般不用，都是用回车表示每一行的结束）
~            匹配，与==相比不是精确比较，类似于java中的contains  比如 '$1~/name/{print $0}'
!~           不匹配，不精确比较
==           等于，必须全部相等，精确比较
!=           不等于，精确比较
&&　         逻辑与
||           逻辑或
+            匹配时表示1个或1个以上(正则规则)
*            没有或者多个

FILENAME 文件名
OFS      输出字段分隔符， 默认也是空格，可以改为制表符等
ORS        输出的记录分隔符，默认为换行符,即处理结果也是一行一行输出到屏幕
-F'[:#/]'   定义三个分隔符

*/


import org.springframework.util.NumberUtils;

/**
print & $0
print 是awk打印指定内容的主要命令
awk '{print}'  /etc/passwd  ==  awk '{print $0}'  /etc/passwd
awk '{print " "}' /etc/passwd                                          //不输出passwd的内容，而是输出相同个数的空行，进一步解释了awk是一行一行处理文本
awk '{print "a"}'   /etc/passwd                                        //输出相同个数的a行，一行只有一个a字母
awk -F":" '{print $1}'  /etc/passwd                                    //以:作为分隔符，打印第一列数据
awk -F: '{print $1; print $2}'   /etc/passwd                           //将每一行的前二个字段，分行输出，进一步理解一行一行处理文本
awk  -F: '{print $1,$3,$6}' OFS="\t" /etc/passwd                       //输出字段1,3,6，以制表符作为分隔符

-f指定脚本文件
awk -f script.awk  file
BEGIN{
FS=":"
}
{print $1}               //效果与awk -F":" '{print $1}'相同,只是分隔符使用FS在代码自身中指定

awk 'BEGIN{X=0} /^$/{ X+=1 } END{print "I find",X,"blank lines."}' config
I find 4 blank lines.
ls -l|awk 'BEGIN{sum=0} !/^d/{sum+=$5} END{print "total size is",sum}'                    //计算文件大小
total size is 17487


-F指定分隔符
$1 指指定分隔符后，第一个字段，$3第三个字段， \t是制表符
一个或多个连续的空格或制表符看做一个定界符，即多个空格看做一个空格
awk -F":" '{print $1}'  /etc/passwd
awk -F":" '{print $1 $3}'  /etc/passwd                              //$1与$3相连输出，不分隔
awk -F":" '{print $1,$3}'  /etc/passwd                              //多了一个逗号，$1与$3使用空格分隔
awk -F":" '{print $1 " " $3}'  /etc/passwd                          //$1与$3之间手动添加空格分隔
awk -F":" '{print "Username:" $1 "\t\t Uid:" $3 }' /etc/passwd      //自定义输出
awk -F: '{print NF}' /etc/passwd                                    //显示每行有多少字段
awk -F: '{print $NF}' /etc/passwd                                   //将每行第NF个字段的值打印出来
awk -F: 'NF==4 {print }' /etc/passwd                                //显示只有4个字段的行
awk -F: 'NF>2{print $0}' /etc/passwd                                //显示每行字段数量大于2的行
awk '{print NR,$0}' /etc/passwd                                     //输出每行的行号
awk -F: '{print NR,NF,$NF,"\t",$0}' /etc/passwd                     //依次打印行号，字段数，最后字段值，制表符，每行内容
awk -F: 'NR==5{print}'  /etc/passwd                                 //显示第5行
awk -F: 'NR==5 || NR==6{print}'  /etc/passwd                        //显示第5行和第6行
route -n|awk 'NR!=1{print}'                                         //不显示第一行




//匹配代码块
//纯字符匹配   !//纯字符不匹配   ~//字段值匹配    !~//字段值不匹配   ~/a1|a2/字段值匹配a1或a2
awk '/mysql/' /etc/passwd
awk '/mysql/{print }' /etc/passwd
awk '/mysql/{print $0}' /etc/passwd                              //三条指令结果一样
awk '!/mysql/{print $0}' /etc/passwd                             //输出不匹配mysql的行
awk '/mysql|mail/{print}' /etc/passwd
awk '!/mysql|mail/{print}' /etc/passwd
awk -F: '/mail/,/mysql/{print}' /etc/passwd                      //区间匹配
awk '/[2][7][7]星号/{print $0}' /etc/passwd                      //匹配包含27为数字开头的行，如27，277，2777...这个要好好理解


 条件表达式
 ==   !=   >   >=    命令里面，也可以写条件判断语句
    awk -F":" '$1=="mysql"{print $3}' /etc/passwd
    awk -F":" '{if($1=="mysql") print $3}' /etc/passwd          //与上面相同
    awk -F":" '$1!="mysql"{print $3}' /etc/passwd               //不等于
    awk -F":" '$3>1000{print $3}' /etc/passwd                   //大于
    awk -F":" '$3>=100{print $3}' /etc/passwd                   //大于等于
    awk -F":" '$3<1{print $3}' /etc/passwd                      //小于
    awk -F":" '$3<=1{print $3}' /etc/passwd                     //小于等于

 逻辑运算符
 &&　||
    awk -F: '$1~/mail/ && $3>8 {print }' /etc/passwd              //逻辑与，$1匹配mail，并且$3>8
    awk -F: '{if($1~/mail/ && $3>8) print }' /etc/passwd
    awk -F: '$1~/mail/ || $3>1000 {print }' /etc/passwd           //逻辑或
    awk -F: '{if($1~/mail/ || $3>1000) print }' /etc/passwd

 数值运算
        awk -F: '$3 > 100' /etc/passwd
        awk -F: '$3 > 100 || $3 < 5' /etc/passwd
        awk -F: '$3+$4 > 200' /etc/passwd
        awk -F: '/mysql|mail/{print $3+10}' /etc/passwd                    //第三个字段加10打印
        awk -F: '/mysql/{print $3-$4}' /etc/passwd                         //减法
        awk -F: '/mysql/{print $3*$4}' /etc/passwd                         //求乘积
        awk '/MemFree/{print $2/1024}' /proc/meminfo                       //除法
        awk '/MemFree/{print int($2/1024)}' /proc/meminfo                  //取整

 输出分隔符OFS
        awk '$6 ~ /FIN/ || NR==1 {print NR,$4,$5,$6}' OFS="\t" netstat.txt
        awk '$6 ~ /WAIT/ || NR==1 {print NR,$4,$5,$6}' OFS="\t" netstat.txt    //输出字段6匹配WAIT的行，其中输出每行行号，字段4，5,6，并使用制表符分割字段

 输出处理结果到文件
        ①在命令代码块中直接输出    route -n|awk 'NR!=1{print > "./fs"}'
        ②使用重定向进行输出           route -n|awk 'NR!=1{print}'  > ./fs

 格式化输出
        netstat -anp|awk '{printf "%-8s %-8s %-10s\n",$1,$2,$3}'
        printf表示格式输出
        %格式化输出分隔符
        -8长度为8个字符
        s表示字符串类型
        打印每行前三个字段，指定第一个字段输出字符串类型(长度为8)，第二个字段输出字符串类型(长度为8),
        第三个字段输出字符串类型(长度为10)
        netstat -anp|awk '$6=="LISTEN" || NR==1 {printf "%-10s %-10s %-10s \n",$1,$2,$3}'
        netstat -anp|awk '$6=="LISTEN" || NR==1 {printf "%-3s %-10s %-10s %-10s \n",NR,$1,$2,$3}'

 IF语句
        awk -F: '{if($3>100) print "large"; else print "small"}' /etc/passwd
        awk -F: 'BEGIN{A=0;B=0} {if($3>100) {A++; print "large"} else {B++; print "small"}} END{print A,"\t",B}' /etc/passwd
        //ID大于100,A加1，否则B加1
        awk -F: '{if($3<100) next; else print}' /etc/passwd                         //小于100跳过，否则显示
        awk -F: 'BEGIN{i=1} {if(i<NF) print NR,NF,i++ }' /etc/passwd
        awk -F: 'BEGIN{i=1} {if(i<NF) {print NR,NF} i++ }' /etc/passwd
        另一种形式
        awk -F: '{print ($3>100 ? "yes":"no")}'  /etc/passwd
        awk -F: '{print ($3>100 ? $3":\tyes":$3":\tno")}'  /etc/passwd

 while语句
        awk -F: 'BEGIN{i=1} {while(i<NF) print NF,$i,i++}' /etc/passwd


 数组
        netstat -anp|awk 'NR!=1{a[$6]++} END{for (i in a) print i,"\t",a[i]}'
        netstat -anp|awk 'NR!=1{a[$6]++} END{for (i in a) printf "%-20s %-10s %-5s \n", i,"\t",a[i]}'
        9523                               1
        9929                               1
        LISTEN                            6
        7903                               1
        3038/cupsd                   1
        7913                               1
        10837                             1
        9833                               1





 应用1
        awk -F: '{print NF}' helloworld.sh                                             //输出文件每行有多少字段
        awk -F: '{print $1,$2,$3,$4,$5}' helloworld.sh                                 //输出前5个字段
        awk -F: '{print $1,$2,$3,$4,$5}' OFS='\t' helloworld.sh                        //输出前5个字段并使用制表符分隔输出
        awk -F: '{print NR,$1,$2,$3,$4,$5}' OFS='\t' helloworld.sh                     //制表符分隔输出前5个字段，并打印行号

 应用2
        awk -F'[:#]' '{print NF}'  helloworld.sh                                       //指定多个分隔符: #，输出每行多少字段
        awk -F'[:#]' '{print $1,$2,$3,$4,$5,$6,$7}' OFS='\t' helloworld.sh             //制表符分隔输出多字段

 应用3
        awk -F'[:#/]' '{print NF}' helloworld.sh                                         //指定三个分隔符，并输出每行字段数
        awk -F'[:#/]' '{print $1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12}' helloworld.sh     //制表符分隔输出多字段

 应用4

        ls -l|awk 'BEGIN{sum=0} !/^d/{sum+=$5} END{print "total size is:",sum/1024,"KB"}'        //计算/home目录下，普通文件的大小，使用KB作为单位
        ls -l|awk 'BEGIN{sum=0} !/^d/{sum+=$5} END{print "total size is:",int(sum/1024),"KB"}'   //int是取整的意思

 应用5
        统计netstat -anp 状态为LISTEN和CONNECT的连接数量分别是多少
        netstat -anp|awk '$6~/LISTEN|CONNECTED/{sum[$6]++} END{for (i in sum) printf "%-10s %-6s %-3s \n", i," ",sum[i]}'

 应用6
        统计/home目录下不同用户的普通文件的总数是多少？
        ls -l|awk 'NR!=1 && !/^d/{sum[$3]++} END{for (i in sum) printf "%-6s %-5s %-3s \n",i," ",sum[i]}'
        mysql        199
        root           374
        统计/home目录下不同用户的普通文件的大小总size是多少？
        ls -l|awk 'NR!=1 && !/^d/{sum[$3]+=$5} END{for (i in sum) printf "%-6s %-5s %-3s %-2s \n",i," ",sum[i]/1024/1024,"MB"}'

 应用7
        输出成绩表
        awk 'BEGIN{math=0;eng=0;com=0;printf "Lineno.   Name    No.    Math   English   Computer    Total\n";printf "------------------------------------------------------------\n"}{math+=$3; eng+=$4; com+=$5;printf "%-8s %-7s %-7s %-7s %-9s %-10s %-7s \n",NR,$1,$2,$3,$4,$5,$3+$4+$5} END{printf "------------------------------------------------------------\n";printf "%-24s %-7s %-9s %-20s \n","Total:",math,eng,com;printf "%-24s %-7s %-9s %-20s \n","Avg:",math/NR,eng/NR,com/NR}' test0

        [root@localhost home]# cat test0
        Marry   2143 78 84 77
        Jack    2321 66 78 45
        Tom     2122 48 77 71
        Mike    2537 87 97 95
        Bob     2415 40 57 62

 */
public class Awk {
    public static void main(String[] args) {
        //System.out.println(NumberUtils.parseNumber("1.535610182016E12",Long.class));
        //System.out.println(org.apache.commons.lang3.math.NumberUtils.createLong("1.535610182016E12"));
        double dou = 1.535610182016E12;
        Double d = new Double(1.234);
        System.out.println(d.longValue());
    }
}
