package com.mysql;

/**
 * @Author suosong
 * @Date 2018/10/26
 =====mysql安装位置=========
ps -ef |grep mariadb
mysql  /usr/libexec/mysqld --basedir=/usr --datadir=/var/lib/mysql --plugin-dir=/usr/lib64/mysql/plugin --log-error=/var/log/mariadb/mariadb.log --pid-file=/var/run/mariadb/mariadb.pid --socket=/var/lib/mysql/mysql.sock

=====mysql架构=============
 mysql的架构牛逼的地方之一是可插拔的存储引擎。
 mysql就像是一部车，而不同的存储引擎就像是不同的轮胎，阴天，晴天，平地，山地等使用不同的轮胎就可以了。而不需要使用不同的车。

 第一层：连接层
    本地的sock通信和远程的tcp/ip通信。在该层上引入了线程池的概念，为连接的客户端提供线程。
 第二层：服务层
    解析查询并创建内部解析树，完成优化。比如确定表的查询顺序，是否使用索引等，最后形成相应的执行操作。如果是select语句，还会有缓存的查询。
    如果缓存够大，在执行大量读操作的时候，会提升不少性能。
 第三次：引擎层
    负责数据的存储与提取。服务器通过api与存储引擎进行通信。
 第四层：存储层

=======存储引擎========
MariaDB [(none)]> show engines;
+--------------------+---------+----------------------------------------------------------------------------+--------------+------+------------+
| Engine             | Support | Comment                                                                    | Transactions | XA   | Savepoints |
+--------------------+---------+----------------------------------------------------------------------------+--------------+------+------------+
| CSV                | YES     | CSV storage engine                                                         | NO           | NO   | NO         |
| MRG_MYISAM         | YES     | Collection of identical MyISAM tables                                      | NO           | NO   | NO         |
| MEMORY             | YES     | Hash based, stored in memory, useful for temporary tables                  | NO           | NO   | NO         |
| BLACKHOLE          | YES     | /dev/null storage engine (anything you write to it disappears)             | NO           | NO   | NO         |
| MyISAM             | YES     | MyISAM storage engine                                                      | NO           | NO   | NO         |
| InnoDB             | DEFAULT | Percona-XtraDB, Supports transactions, row-level locking, and foreign keys | YES          | YES  | YES        |
| ARCHIVE            | YES     | Archive storage engine                                                     | NO           | NO   | NO         |
| FEDERATED          | YES     | FederatedX pluggable storage engine                                        | YES          | NO   | YES        |
| PERFORMANCE_SCHEMA | YES     | Performance Schema                                                         | NO           | NO   | NO         |
| Aria               | YES     | Crash-safe tables with MyISAM heritage                                     | NO           | NO   | NO         |

MariaDB [(none)]> show variables like '%storage%';
+------------------------+--------+
| Variable_name          | Value  |
+------------------------+--------+
| default_storage_engine | InnoDB |
| storage_engine         | InnoDB |


======sql语句执行顺序========
 from
 join on
 where
 group by
 having
 select
 distinct
 order by
 limit
课程有个鱼刺图，看不太懂。



 */
public class Mysql02 {
}
