package com.algorithm.sword_offer;

/**
 * 矩阵中的路径
 * 可以通过回溯法来实现该算法
 */
public class Demo65 {

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || rows < 1 || cols < 1 || str == null) {
            return false;
        }
        int[] visited = new int[matrix.length];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasHelper(matrix, rows, cols, i, j, 0, str, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasHelper(char[] matrix, int rows, int cols, int i, int j, int k, char[] str, int[] visited) {
        int index = i * cols + j;
        if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k] ||visited[index]==1) {
            return false;
        }

        if(k==str.length-1){
            System.out.println(matrix[index]);
            return true;
        }
        visited[index] = 1;
        if (hasHelper(matrix, rows, cols, i-1, j, k + 1, str, visited)
                || hasHelper(matrix, rows, cols, i+1, j, k + 1, str, visited)
                || hasHelper(matrix, rows, cols, i, j-1, k + 1, str, visited)
                || hasHelper(matrix, rows, cols, i, j+1, k + 1, str, visited)) {
            System.out.println(matrix[index]);
            return true;
        }
        visited[index] = 0;
        return false;
    }

    public static void main(String[] args) {
        char[] matrix={'a','b','c','e','s','f','c','s','a','d','e','e'};
        char[] str = {'b','c','c','e','d'};
        Demo65 d = new Demo65();
        System.out.println(d.hasPath(matrix,3,4,str));
    }

}
