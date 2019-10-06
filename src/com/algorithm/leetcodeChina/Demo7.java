package com.algorithm.leetcodeChina;

/**
 * 整数反转
 */
public class Demo7 {

    public int reverse(int x) {
        if(x==0){
            return x;
        }
        String str = String.valueOf(x);
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        if (chars[0] == '-' || chars[0] == '+') {
            sb.append(chars[0]);
            for (int i = chars.length - 1; i > 0; i--) {
                if (i == chars.length - 1 && chars[i] == '0') {
                    continue;
                } else {
                    sb.append(chars[i]);
                }
            }
        } else {
            for (int i = chars.length - 1; i >= 0; i--) {
                if (i == chars.length - 1 && chars[i] == '0') {
                    continue;
                } else {
                    sb.append(chars[i]);
                }
            }
        }
        int a = 0;
        try{
            a = Integer.valueOf(sb.toString());
        }catch(Exception e){

        }
        return a;
    }

    public static void main(String[] args) {
        Demo7 d = new Demo7();
        int a = 1534236469;
        System.out.println(d.reverse(a));
    }
}
