package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 回文字符串
 *
 * @Author : yinchao
 * @create 2020/5/24 3:23 下午
 */
public class Demo647 {
    public int countSubstrings(String s) {
        if (s==""||s==null) {
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int result = n;
        for (int i = n - 1; i >= 0; i++) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }
                if (dp[i][j]) {
                    result++;
                }

            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s = "aaa";
        Demo647 d = new Demo647();
        System.out.println(d.countSubstrings(s));
    }
}
