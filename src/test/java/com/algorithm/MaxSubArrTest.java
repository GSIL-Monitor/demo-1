package com.algorithm;

import org.junit.Before;
import org.junit.Test;

public class MaxSubArrTest {

    int[] arr;
    MaxSubArr maxSubArr;

    @Before
    public void setup(){
        arr = new int[]{1,2,3,4,5,6,7,8,9};
        //arr = new int[]{1,2,3,4};
        arr = new int[]{-1,2,1,-3,5,5,-2,1,4};
        maxSubArr = new MaxSubArr();
    }

    @Test
    public void maxSubArrSum() throws Exception {
        int max = maxSubArr.maxSubArrSum(arr,0,arr.length-1);
        System.out.println(max);
    }

    @Test
    public void max_sub_position() throws Exception {
        int sum = maxSubArr.max_sub_position(arr,2,0,arr.length-1);
        System.out.println(sum);
    }

}