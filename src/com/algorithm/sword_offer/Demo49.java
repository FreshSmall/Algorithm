package com.algorithm.sword_offer;


/**
 * 将字符串转化为整数
 */
public class Demo49 {


    public int StrToInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        char[] numbers = str.toCharArray();
        int fuhao = 0;
        if (numbers[0] == '-') {
            fuhao = 1;
        }
        int sum = 0;
        for (int i = fuhao; i < numbers.length; i++) {
            if (numbers[i] == '+') {
                continue;
            }
            if (numbers[i] < 48 || numbers[i] > 57) {
                return 0;
            }
            sum = 10 * sum + (numbers[i] - 48);

        }
        return fuhao==1?sum*-1:sum;
    }

    public static void main(String[] args) {
        Demo49 d = new Demo49();
        System.out.println(d.StrToInt("-123133"));
    }
}
