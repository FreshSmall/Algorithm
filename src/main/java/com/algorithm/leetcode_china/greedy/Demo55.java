package com.algorithm.leetcode_china.greedy;

/**
 * Created by IntelliJ IDEA.
 * 跳跃游戏
 *
 * @Author : yinchao
 * @create 2020/5/26 9:00 下午
 */
public class Demo55 {

    public boolean canJump(int[] nums) {
        int reach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i>reach) {
                return false;
            }
            reach = Math.max(i+nums[i],reach);
        }
        return true;
    }
}
