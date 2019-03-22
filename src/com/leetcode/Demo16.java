package com.leetcode;

import java.util.Arrays;

/**
 * 动态规划，题目：给糖果
 * 每个孩子至少一个糖果
 * 高等级的小孩，要比相邻低等级的小孩至少多一个糖果
 */
public class Demo16 {

    public int candy(int[] ratings) {

        int[] nums = new int[ratings.length];
        Arrays.fill(nums,1);
        int sum = 0;

        for(int i=0;i<ratings.length-1;i++){
            if(ratings[i]>ratings[i+1]&&nums[i]<=nums[i+1]){
                nums[i]=nums[i+1]+1;
            }else if(ratings[i]<ratings[i+1]&&nums[i]>=nums[i+1]){
                nums[i+1] =nums[i]+1;
            }

        }

        for(int i =ratings.length-1;i>0;i--){
            if(ratings[i]>ratings[i-1]&&nums[i]<=nums[i-1]){
                nums[i]=nums[i-1]+1;
            }else if(ratings[i]<ratings[i-1]&&nums[i]>=nums[i-1]){
                nums[i-1] =nums[i]+1;
            }
        }


        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] rating = {5,3,1};
        Demo16 d = new Demo16();
        System.out.println(d.candy(rating));
    }
}
