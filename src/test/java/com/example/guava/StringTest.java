package com.example.guava;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.StringJoiner;

/**
 * @Author suosong
 * @Date 2018/7/6
 */
public class StringTest {
    @Test
    public void test_join(){
        Joiner joiner = Joiner.on(",");
        StringBuilder sb = new StringBuilder();
        Integer[] integers = {1,2,3,4,5,null,7};
        //appendTo重载的方法有Object[] Iterable Iterator 代码都重用了。
        //不能有null，否则会报空指针
        joiner.appendTo(sb,integers);
        System.out.println(sb);
        //底层调用的toAppend
        System.out.println(joiner.join(integers));

    }
    @Test
    public void test_join2(){
        Integer[] integers = {1,2,3,4,5,null,7};
        //新的对象是Joiner的子类，重写了appendTo方法。
        Joiner joiner = Joiner.on(",").skipNulls();
        System.out.println(joiner.join(integers));
    }


}
