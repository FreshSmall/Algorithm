package com.algorithm.leetcode;

/**
 * 一个整数数组，其中除了一个数其他数全部出现了三次，找出其中没有出现过三次的数
 */
public class Demo14 {

    public int singleNumber(int[] A) {
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int i = 0;

        for (i = 0; i < A.length; i++) {
            twos |= ones & A[i];
            ones ^= A[i];
            threes = ones & twos;
            ones &= ~threes;
            twos &= ~threes;

        }
        return ones;
    }


    public static void main(String[] args) {
        int[] A = {3, 3, 3, 2};
        Demo14 dd = new Demo14();
        int a = dd.singleNumber(A);
        System.out.println(a);
    }

}
