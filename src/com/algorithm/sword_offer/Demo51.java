package com.algorithm.sword_offer;

/**
 * 构建乘积数组
 */
public class Demo51 {

    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        for(int i =0;i<A.length;i++){
            int sumB = 1;
            for(int j=0;j<i;j++){
                sumB*=A[j];
            }

            for(int j=i+1;j<A.length;j++){
                sumB*=A[j];
            }
            B[i]=sumB;
        }
        return B;
    }

    public static void main(String[] args) {

    }
}
