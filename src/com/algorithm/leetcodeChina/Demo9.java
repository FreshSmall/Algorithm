package com.algorithm.leetcodeChina;

/**
 * 判断一个整数是否为回文数
 */
public class Demo9 {

    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        char[] strs = str.toCharArray();
        int i = 0;
        int j = str.length()-1;
        while(i<=j){
            if(strs[i]!=strs[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
