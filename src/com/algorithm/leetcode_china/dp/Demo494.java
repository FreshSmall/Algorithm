package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 目标和
 *
 * @Author : yinchao
 * @create 2020/5/24 2:52 下午
 */
public class Demo494 {

    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (S > sum || (S + sum) % 2 == 1) {
            return 0;
        }
        int target = (S + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[target];

    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 1, 1, 1};
        Demo494 d = new Demo494();
        System.out.println(d.findTargetSumWays(a, 3));
    }
}
