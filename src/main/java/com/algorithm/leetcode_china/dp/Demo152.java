package com.algorithm.leetcode_china.dp;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 乘积最大子数组
 * @Author : yinchao
 * @create 2020/5/17 1:43 下午
 */
public class Demo152 {

    public int maxProduct(int[] nums) {
        int number = nums.length;
        int[] dpmax = new int[number+1];
        int[] dpmin = new int[number+1];

        Arrays.fill(dpmax,1);
        Arrays.fill(dpmin,1);
        dpmin[0] = Integer.MIN_VALUE;
        dpmax[0] = Integer.MIN_VALUE;
        dpmax[1] = nums[0];
        dpmin[1] = nums[0];

        for(int i=1;i<number;i++){
            dpmax[i+1] = Math.max(dpmax[i]*nums[i],Math.max(dpmin[i]*nums[i],nums[i]));
            dpmin[i+1] = Math.min(dpmax[i]*nums[i],Math.min(dpmin[i]*nums[i],nums[i]));
        }
        Arrays.sort(dpmax);
        return dpmax[number];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-3,0,-4};
        Demo152 d = new Demo152();
        System.out.println(d.maxProduct(nums));
    }
}
