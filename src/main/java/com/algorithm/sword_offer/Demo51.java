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

    public int[] multiply1(int[] A) {
        int[] B = new int[A.length];
        B[0]=1;
        //计算下三角
        for(int i=1;i<A.length;i++){
            B[i]=B[i-1]*A[i-1];
        }
        int temp = 1;
        //计算上三角
        for(int i=A.length-2;i>=0;i--){
            temp*=A[i+1];
            B[i]*=temp;
        }
        return B;
    }

    public static void main(String[] args) {
        Demo51 d = new Demo51();
        int[] A = {1,2,3,4,5};
        int[] B = d.multiply1(A);
        for (int i = 0; i < B.length; i++) {
            System.out.println(B[i]);
        }
    }
}
