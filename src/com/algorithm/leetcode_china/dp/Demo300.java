package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 最长上升子序列
 *
 * @Author : yinchao
 * @create 2020/5/18 9:02 下午
 */
public class Demo300 {

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int maxLen1 = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxLen1 = Math.max(maxLen1, dp[j]);
                }
            }
            dp[i] = maxLen1 + 1;
            maxLength = Math.max(dp[i], maxLength);
            maxLen1 = 0;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 10, 4, 3, 8, 9};
        Demo300 d = new Demo300();
        System.out.println(d.lengthOfLIS(a));
    }
}
