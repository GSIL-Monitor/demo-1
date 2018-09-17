package com.algorithm;

/**
 * 最大连续子数组求解




 */
public class MaxSubArr {


    public int maxSubArrSum(int[] arr, int leftIndex, int rightIndex) {
        //1,递归结束条件
        if (leftIndex >= rightIndex) {
            return arr[rightIndex];
        }
        //2,将数组分成两半
        int midIndex = (leftIndex + rightIndex)/2;
        //3,求左半边的最大子数组和
        int leftMaxSum = maxSubArrSum(arr,leftIndex,midIndex);
        //4,求右半边最大子数组和
        int rightMaxSum = maxSubArrSum(arr, midIndex+1,rightIndex);
        //5,求中间最大子数组和
        int midMaxSum = max_sub_position(arr,midIndex,leftIndex,rightIndex);
        //6,求出这三个数的最大值
        int temp;
        return (temp = leftMaxSum > midMaxSum? leftMaxSum:midMaxSum)>rightMaxSum? temp: rightMaxSum;
    }


    /**
     * 已知一个数组的最大连续子数组的其中一个位置,求这个最大子数组
     *
     * @param arr
     * @param positionIndex
     * @return
     */
    public int max_sub_position(int[] arr, int positionIndex, int left_edge_index, int right_edge_index) {
        //求左半边的最大值
        int left_max_sum = 0;
        int temp_max_sum = 0;
        int currentIndex = positionIndex;
        while (currentIndex >= left_edge_index) {
            temp_max_sum = temp_max_sum + arr[currentIndex--];
            if (temp_max_sum > left_max_sum) {
                left_max_sum = temp_max_sum;
            }
        }
        //求右半边的最大值
        int right_max_sum = 0;
        temp_max_sum = 0;
        currentIndex = positionIndex + 1;
        while (currentIndex <= right_edge_index) {
            temp_max_sum = temp_max_sum + arr[currentIndex++];
            if (temp_max_sum > right_max_sum) {
                right_max_sum = temp_max_sum;
            }
        }
        //返回
        return left_max_sum + right_max_sum;
    }
}

