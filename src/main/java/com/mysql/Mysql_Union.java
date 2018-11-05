package com.mysql;

/**
 * @Author suosong
 * @Date 2018/10/27
 全集合，就需要union.
 union自带去重的功能。
 A的左连接 union A的右连接。
 结果就是全集合。所谓全集合，就是A,B两个表的数据都有，符合连接条件的，有值。不符合连接条件的，为null
 select * from A left join B on A.id = B.id
 union
 select * from A right join B on A.id= B.id

 */
public class Mysql_Union {
}
