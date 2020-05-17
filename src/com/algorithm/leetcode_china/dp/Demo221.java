package com.algorithm.leetcode_china.dp;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 最大正方形
 *
 * @Author : yinchao
 * @create 2020/5/17 2:34 下午
 */
public class Demo221 {

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if(row == 0){
            return 0;
        }
        int lin = matrix[0].length;
        int[][] dp = new int[row][lin];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < lin; j++) {
                if (matrix[i][j] != '0') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = Integer.valueOf(String.valueOf(matrix[i][j]));
                    } else if (i > 0 && j > 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            Arrays.sort(dp[i]);
            if (max < dp[i][lin - 1]) {
                max = dp[i][lin - 1];
            }
        }
        return max*max;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        Demo221 d = new Demo221();
        System.out.println(d.maximalSquare(matrix));
    }
}
