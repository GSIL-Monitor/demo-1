package com.mysql;

/**
 * @Author suosong
 * @Date 2018/10/27


MariaDB [test]> explain
-> select * from student join city on student.city_id = city.id and city.id = 3;
+------+-------------+---------+-------+---------------+------------+---------+-------+------+-------+
| id   | select_type | table   | type  | possible_keys | key        | key_len | ref   | rows | Extra |
+------+-------------+---------+-------+---------------+------------+---------+-------+------+-------+
|    1 | SIMPLE      | city    | const | PRIMARY       | PRIMARY    | 4       | const |    1 |       |
|    1 | SIMPLE      | student | ref   | idx_cityId    | idx_cityId | 5       | const |    1 |       |
+------+-------------+---------+-------+---------------+------------+---------+-------+------+-------+---------+---------+-------+---------------+---------+---------+-------+------+-------------+

    id:
        如果id相同，按照顺序执行
        如果id不同，id越大，优先级越高
    select_type:
        simple: 简单查询，不包含子查询
        primary: 查询中若包含任意复杂查询，最外层会被标记为primary。比如子查询的最外层。也可以理解为最后执行的那个。
        subquery: 子查询
        derived: from列表中的子查询被称为衍生(derived)，mysql会递归的执行这些子查询并把结果放在临时表里面
    type:
        这个特征很重要，做的优化好不好，这个特征很关键。从最差到最好依次为：
        all: 全表扫描
        index: full index scan 全索引查找，比all好一点 比如 explain select id from city;
        range: 给定一个范围，从这个范围扫描。使用一个索引来固定这个范围
        ref: 非唯一性索引扫描，返回符合记录的所有行。比如非唯一索引
        const: 常量，索引一次就找到了，不是primary key 就是unique index 。
        system: 表里只有一条数据。在临时表里很常见
        在实际的工作中，能达到range就可以了，能达到ref最好。
    possible_keys：
        涉及到所查字段的索引，一个或者多个，可能用可能不用
    key:
        查询过程中用到的索引，如果为null，表示没有使用索引
    ref：
        只索引的值等于什么。可以等于const，也可以等于其他表的字段。比如上面的例子。
        当两个表做jion的时候，肯定有个表示驱动表，为全表扫描（student表）。另外一个表就可以使用索引可以等于student.city_id，但是这里为const。如果没有city.id=3的条件，就不是const了
        当 select * from city where id=4 时，ref 就为const
    rows:
        每张表被查询的行数，所有行数加起来就是查询结果一共所查询的行数
    Extra：
        包含不适合在其他列中显示但又是十分重要的额外信息
        using filesort: mysql无法利用索引完成的排序（order by 非索引字段），称为"文件排序"。出现这种情况，比较危险，应该尽量避免这种情况出现
        using temporary: 是用来临时表来存储中间结果，临时表常见于 order by 于 group by 操作。 出现这种情况，说明必须要改进了。
        using index: 很理想的情况。表示select操作使用了覆盖索引（只查索引，不查表），效率很高。
                        如果同时出现了using where 表明索引同时被用来进行索引键值得查找
                        如果没有出现using where 表明只是用到了索引。没有用到表。
        using where: 表明使用了where过滤
        using join buffer: 使用了连接缓存
        impossible where : where 子句的值总是false，不能用来获取元组

 */
public class Mysql_Explain {
}
