package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 不同二叉搜索树
 *
 * @Author : yinchao
 * @create 2020/5/15 5:01 下午
 */
public class Demo96 {

    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[0] = 1;
        for (int i = 2; i <=n; i++) {
            for (int j = 1; j <=i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }

        }
        return dp[n];
    }

    public static void main(String[] args) {
        Demo96 d = new Demo96();
        System.out.println(d.numTrees(3));
    }
}
