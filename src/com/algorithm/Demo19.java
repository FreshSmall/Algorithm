package com.algorithm;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 */
public class Demo19 {

    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int top = 0;
        int left = 0;
        int bottom = matrix[0].length;
        int right = matrix.length;

        while(top<=bottom&&left<=right){
            //从左向右打印
            for(int i=left;i<right-1;i++){
                System.out.println(matrix[top][i]);
            }
            //从上向下打印
            for (int i=top;i<bottom-1;i++){
                System.out.println(matrix[right][top]);
            }
            //从右向左打印
            for(int i=right-1;i>left;i--){
                System.out.println(matrix[bottom][i]);
            }
            //从下向上打印
            for(int i=bottom-1;i>top;i--){
                System.out.println(matrix[i][left]);
            }
            //矩阵缩小向内缩小一圈
            top++;right--;bottom--;left--;
        }
        return null;
    }

    public static void main(String[] args) {
        /*int[][] matrix={{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        Demo19 d = new Demo19();
        d.printMatrix(matrix);*/
        System.out.println(1);
    }
}
