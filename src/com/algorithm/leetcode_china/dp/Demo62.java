package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 不同路径
 *
 * @Author : yinchao
 * @create 2020/5/15 4:18 下午
 */
public class Demo62 {

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 1;
                } else if (i > 0 && j >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

            }
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        Demo62 d = new Demo62();
        System.out.println(d.uniquePaths(3, 2));
    }
}
