package com.tx;

import com.mybatis.Mybatis01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author suosong
 * @Date 2018/10/22
 */
@Component
public class Method01 {
    @Autowired
    Mybatis01 mybatis01;

    public int updateTest01(){
        return mybatis01.updateVersion();
    }
    //@Transactional
    public void updateTest02(){
        updateTest01();
        throw new RuntimeException("Method01.updateTest02 .. error ...");
    }
}
