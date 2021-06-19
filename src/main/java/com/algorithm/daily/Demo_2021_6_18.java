/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.algorithm.daily;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/6/18 11:53 下午
 **/
public class Demo_2021_6_18 {

    public static String smallestGoodBase(String n) {
        Long num = Long.parseLong(n);
        int bitCount = (int) Math.floor(Math.log(num) / Math.log(2));
        for (int i = bitCount; i > 1; i--) {
            int k = (int) (Math.pow(num, 1.0 / i));
            long target = 1;
            long index = 1;
            for (int j = 1; j <= i; j++) {
                index *= k;
                target += index;
            }
            if (num == target) {
                return String.valueOf(k);
            }
        }
        return String.valueOf(num - 1);
    }

    public static String smallestGoodBase1(String n) {
        long nVal = Long.parseLong(n);
        int mMax = (int) Math.floor(Math.log(nVal) / Math.log(2));
        for (int m = mMax; m > 1; m--) {
            int k = (int) Math.pow(nVal, 1.0 / m);
            long mul = 1, sum = 1;
            for (int i = 0; i < m; i++) {
                mul *= k;
                sum += mul;
            }
            if (sum == nVal) {
                return Integer.toString(k);
            }
        }
        return Long.toString(nVal - 1);
    }



    public static void main(String[] args) {
        System.out.println(smallestGoodBase("2251799813685247"));
        System.out.println(smallestGoodBase1("2251799813685247"));
        System.out.println((int) Math.pow(13, 1.0 / 3));
    }

}
