package com.algorithm.leetcode_china.dp;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 零钱兑换
 *
 * @Author : yinchao
 * @create 2020/5/15 5:51 下午
 */
public class Demo322 {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] =0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int payFunction(int[] coins, int cash) {
        int[] dp = new int[cash + 1];
        dp[0] =1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <=cash; j++) {
                dp[j] += dp[j-coins[i]];
            }
        }
        return dp[cash];
    }



    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        Demo322 d = new Demo322();
        System.out.println(d.payFunction(coins, 6));
    }
}
