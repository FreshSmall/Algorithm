package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 最佳股票买卖时期含冷冻期
 *
 * @Author : yinchao
 * @create 2020/5/19 9:17 下午
 */
public class Demo309 {

    public int maxProfit(int[] prices) {
        /**
         * dp[i][0]表示第i天手里没股票并且当天没有卖股票的收益
         * dp[i][1]表示第i天手里有股票的收益
         * dp[i][2]表示第i天手里没有股票并且当天卖了股票的收益
         */
        int days = prices.length;
        if (days==0) {
            return 0;
        }
        int[][] dp = new int[days + 1][3];
        dp[1][0] = 0;
        dp[1][1] = -1 * prices[0];
        dp[1][2] = 0;
        for (int i = 1; i < days; i++) {
            dp[i+1][0] = Math.max(dp[i][0], dp[i][2]);
            dp[i+1][1] = Math.max(dp[i][1], dp[i][0] - prices[i]);
            dp[i+1][2] = dp[i][1] + prices[i];
        }
        return Math.max(dp[days][0], dp[days][2]);
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,3,0,2};
        Demo309 d = new Demo309();
        System.out.println(d.maxProfit(prices));
    }
}
