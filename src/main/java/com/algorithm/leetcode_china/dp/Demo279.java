package com.algorithm.leetcode_china.dp;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 完全平方数
 *
 * @Author : yinchao
 * @create 2020/5/18 8:38 下午
 */
public class Demo279 {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i],dp[i-j*j]+1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Demo279 d = new Demo279();
        System.out.println(d.numSquares(13));
    }
}
