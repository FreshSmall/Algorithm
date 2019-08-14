package com.algorithm.sword_offer;

/**
 * 连续子数组的最大和
 */
public class Demo30 {


    public static void main(String[] args) {
        int[] array = {1, -2, 3, 10, -4, 7, 2, -5};
        if (array == null || array.length == 0) {

        }
        int curNum = 0;
        int greastNum = 0x80000000;
        for (int i = 0; i < array.length; i++) {
            if (curNum <= 0) {
                curNum = array[i];
            } else {
                curNum += array[i];
            }
            if (curNum >= greastNum) {
                greastNum = curNum;
            }
        }

        System.out.println(greastNum);
    }
}
