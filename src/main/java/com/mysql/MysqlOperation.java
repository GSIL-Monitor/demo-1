package com.mysql;

import java.util.Random;

/**
 * mysql的操作
 *
 * @Author suosong
 * @Date 2018/8/20
 * <p>
 * </p>
 * 删除字段：
 * alter table table_name drop [column] age,drop [column] address;
 * 增加字段：
 * alter table table_name add [column] age int,add [column] address varchar(11);
 * <p>
 * 删除索引：
 * alter table table_name drop index index_name;
 * 增加索引：
 * alter table table_name add [unique]index index_name(field1,field2...);
 */
public class MysqlOperation {


    public static void main(String[] args) {
        for (int k = 0; k < 10; k++) {
            int exp = 0;
            int total = 10;
            int reach = 7;
            int times = 1000;
            for (int i = 0; i < times; i++) {
                exp += getProperties(reach, total);
            }
            System.out.println("每" + total + "个命中" + reach + "个，重复" + times + "次,一共命中： " + exp + " 次");
        }
        System.out.println(3600*24*365*3);

    }

    /**
     * 根据概率返回值，命中是1，不命中是0
     *
     * @param reach 分子
     * @param total 分母
     * @return
     */
    public static int getProperties(int reach, int total) {
        int i = new Random().nextInt(total);
        if (i < reach) return 1;//命中
        return 0;
    }
}
