package com.mybatis;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
public interface Mybatis01 {
    @Update("update test01 set version = 2")
    int updateVersion();
}
