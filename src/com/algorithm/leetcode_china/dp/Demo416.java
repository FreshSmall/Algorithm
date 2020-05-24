package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 分割等和子集
 *
 * @Author : yinchao
 * @create 2020/5/20 9:10 下午
 */
public class Demo416 {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((sum & 1) != 0) {
            return false;
        }
        int len = nums.length;
        int target = sum / 2;
        boolean[][] dp = new boolean[len][target + 1];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0) {
                    if (j == nums[0]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (j > nums[i]) {
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }

            }
        }
        return dp[len-1][target];

    }

    public static void main(String[] args) {
        int[] a =new int[]{1, 2, 3, 6};
        Demo416 d = new Demo416();
        System.out.println(d.canPartition(a));
    }
}
