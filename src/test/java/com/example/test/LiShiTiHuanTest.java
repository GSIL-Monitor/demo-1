package com.example.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author suosong
 * @Date 2018/7/2
 */
public class LiShiTiHuanTest {

    @Test
    public void test(){
        Chinese chinese = new ShanDongRen();
        System.out.println(chinese.say());
    }


    @Test
    public void test02(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        System.out.println(list);

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        System.out.println(list2);

        List<Integer> list3 = new ArrayList<>(list2);
        System.out.println(list3);

        List<Integer> list4 = new ArrayList(Arrays.asList(new int[]{1,2,3}));
        System.out.println(list4);

    }

    @Test
    public void test03(){
        List<?> list = Arrays.asList( new int[]{1,2,3});
        System.out.println(list.size());
        System.out.println("==========================");
        List<?> list1 = Arrays.asList( new Integer[]{1,3,5});
        System.out.println(list1.size());
    }

    @Test
    public void test04(){
        int[] arr1 = new int[]{1,2,3};
        Integer[] arr2 = new Integer[]{1,2,3};
        System.out.println(arr1.getClass().getName());
        System.out.println(arr2.getClass().getName());
    }

    @Test
    public void test05(){
        add(new int[]{1,2,3});
    }
    private void add(int... ints){
        System.out.println(ints);
        System.out.println(ints.length);
    }


    static class Chinese{
        String say(){
            return "中国人";
        }
    }

    static class HuaBeiRen extends Chinese{
        @Override
        String say(){
            return "华北人";
        }
    }

    static class ShanDongRen extends HuaBeiRen{
        //@Override
        String say1(){
            return "山东人";
        }
    }
}
