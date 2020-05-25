package com.algorithm.leetcode_china.stack;

/**
 * 每日温度
 */
public class Demo739 {

    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Demo739 d = new Demo739();
        int[] T = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] result = d.dailyTemperatures(T);
        System.out.println(result.length);
    }

}
