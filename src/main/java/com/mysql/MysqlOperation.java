package com.mysql;

/**
 * mysql的操作
 * @Author suosong
 * @Date 2018/8/20
 *



 删除字段：
    alter table table_name drop [column] age,drop [column] address;
 增加字段：
    alter table table_name add [column] age int,add [column] address varchar(11);

 删除索引：
    alter table table_name drop index index_name;
 增加索引：
    alter table table_name add [unique]index index_name(field1,field2...);
 */
public class MysqlOperation {
}
