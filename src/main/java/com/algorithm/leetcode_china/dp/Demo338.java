package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 比特位计数
 *
 * @Author : yinchao
 * @create 2020/5/20 8:49 下午
 */
public class Demo338 {


    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        int b = 1;
        int i = 0;
        while (b <= num) {
            while (b + i <= num) {
                dp[i + b] = dp[i] + 1;
                i++;
            }
            i = 0;
            b <<= 1;
        }
        return dp;
    }

    public int[] countBits1(int num) {
        int[] dp = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            dp[i] = dp[i >> 1] + i & 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        Demo338 d = new Demo338();
        System.out.println(d.countBits1(2));
    }
}
