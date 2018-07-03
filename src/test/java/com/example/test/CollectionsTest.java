package com.example.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/7/2
 */
public class CollectionsTest {
    @Test
    public void test_shuffle(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        Collections.shuffle(list);
        System.out.println(list);

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        Collections.shuffle(list);
        System.out.println(list);

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        Collections.shuffle(list);
        System.out.println(list);
    }

    @Test
    public void test_System_arraycopy(){
        int[] a = {1,2,3,4,5};
        int[] copy = new int[5];
        System.arraycopy(a,0,copy,0,3);

        for(int i = 0;i<copy.length;i++){
            System.out.println(copy[i]);
        }
        System.out.println("=================");
        List<Integer> list = new ArrayList( Arrays.asList(copy));
        System.out.println(list);
        //ArrayStoreException
        //Arrays.asList()
    }
}
