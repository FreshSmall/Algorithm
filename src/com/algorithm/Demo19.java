package com.algorithm;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 */
public class Demo19 {

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int top = 0;
        int left = 0;
        int right = matrix[0].length;
        int bottom = matrix.length;

        while(top<bottom&&left<right){
            //从左向右打印
            for(int i=left;i<right;i++){
                System.out.println(matrix[top][i]);
            }
            //从上向下打印
            for (int i=top+1;i<bottom-1;i++){
                System.out.println(matrix[i][right-1]);
            }
            //从右向左打印
            if(top!=bottom-1){
                for(int i=right-1;i>=left;i--){
                    System.out.println(matrix[bottom-1][i]);
                }
            }
            //从下向上打印
            if(left!=right-1){
                for(int i=bottom-2;i>top;i--){
                    System.out.println(matrix[i][left]);
                }
            }
            //矩阵缩小向内缩小一圈
            top++;right--;bottom--;left++;
        }
        return null;
    }

    public static void main(String[] args) {
        int[][] matrix={{1,2},{3,4}};
        Demo19 d = new Demo19();
        d.printMatrix(matrix);
    }
}
