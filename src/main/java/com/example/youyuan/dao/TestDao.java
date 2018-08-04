package com.example.youyuan.dao;

import com.example.youyuan.pojo.Test01;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/8/3
 */
@Mapper
public interface TestDao {
    @Insert("insert into test01(name,age,add_time)values(#{name},#{age},#{addTime})")
    void insertTest01(Test01 test01);

    @Select("select * from test01")
    List<Test01> getTest01List();

    @Update("update test01 set name=#{name} where id=#{id}")
    void updateTestO1(@Param("name") String name,@Param("id") long id);

    @Update("update test01 set update_time=#{update_time} where id=#{id}")
    void updateTest01_time(@Param("update_time") Date updateTime,@Param("id")long id);

}
