package com.algorithm;

/**
 * 1~n整数中1出现的次数
 */
public class Demo31 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int num = 0;
        for (int i = 1; i <= n; i++) {
            num += getNumOf1(i);
        }
        return num;
    }

    public int getNumOf1(int n) {
        int number = 0;
        String num = String.valueOf(n);
        char[] charNum = num.toCharArray();
        for (int i = 0; i < charNum.length; i++) {
            if (charNum[i] == '1') {
                ++number;
            }
        }
        return number;
    }

    public static void main(String[] args) {
        int n = 1;
        Demo31 demo31 = new Demo31();
        int a = demo31.NumberOf1Between1AndN_Solution(1);
        System.out.println(a);
    }
}
