package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 最小路劲和
 *
 * @Author : yinchao
 * @create 2020/5/15 4:42 下午
 */
public class Demo64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                } else if (i == 0) {
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i-1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);

                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {

    }
}
