package com.algorithm.sword_offer;

/**
 * 求从小到大的第N个丑数
 */
public class Demo33 {

    public int GetUglyNumber_Solution(int index) {
        int[] pUglyNumbers = new int[index];
        pUglyNumbers[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int num = 0;
        while (num < index - 1) {
            int min = getMin(pUglyNumbers[i2] * 2, pUglyNumbers[i3] * 3, pUglyNumbers[i5] * 5);
            if (min == pUglyNumbers[i2] * 2) {
                i2++;
            }
            if (min == pUglyNumbers[i3] * 3) {
                i3++;
            }
            if (min == pUglyNumbers[i5] * 5) {
                i5++;
            }
            num++;
            pUglyNumbers[num] = min;
        }
        return pUglyNumbers[index - 1];
    }

    private int getMin(int i, int i1, int i2) {
        int min = (i < i1) ? i : i1;
        min = (min < i2) ? min : i2;
        return min;
    }

    public static void main(String[] args) {
        Demo33 d = new Demo33();
        int a = d.GetUglyNumber_Solution(2);
        System.out.println(a);
    }
}
