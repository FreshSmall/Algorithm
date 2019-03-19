package com.algorithm;

/**
 * 机器人的运动范围
 */
public class Demo66 {

    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] flag = new boolean[rows][cols];
        return helper(threshold, rows, cols, 0, 0, flag);
    }

    private int helper(int threshold, int rows, int cols, int i, int j, boolean[][] flag) {
        int count = 0;
        if (i < 0 || i >= rows || j < 0 || j >= cols || (getNum(i) + getNum(j)) > threshold || flag[i][j]) {
            return 0;
        }
        System.out.println("getNum(i) = " + getNum(i));
        flag[i][j] = true;
        count = helper(threshold, rows, cols, i - 1, j, flag) +
                helper(threshold, rows, cols, i + 1, j, flag) +
                helper(threshold, rows, cols, i, j - 1, flag) +
                helper(threshold, rows, cols, i, j + 1, flag) + 1;

        return count;
    }

    private int getNum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }
        return sum;
    }

    public static void main(String[] args) {

        Demo66 d = new Demo66();
        int count = d.movingCount(5, 10, 10);
        System.out.println("count = " + count);


    }
}
