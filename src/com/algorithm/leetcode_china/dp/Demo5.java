package com.algorithm.leetcode_china.dp;

/**
 * Created by IntelliJ IDEA.
 * 回文字符串
 * @Author : yinchao
 * @create 2020/5/14 11:40 下午
 */
public class Demo5 {

    public String longestPalindrome(String s) {
        int len = s.length();
        int start = 0;
        int maxLen = 0;
        for(int i=0;i<len;i++){
            int cur = Math.max(getMaxLen(i,i,len,s),getMaxLen(i,i+1,len,s));
            if(cur>maxLen){
                maxLen = cur;
                start = i-(maxLen-1)/2;
            }
        }
        return s.substring(start,start+maxLen);
    }

    public int getMaxLen(int l,int r,int len,String s){
        while(l>=0&&r<len&&s.charAt(l)==s.charAt(r)){
            --l;
            ++r;
        }
        return r-l-1;
    }

    public static void main(String[] args) {
        String str = "babad";
        Demo5 d = new Demo5();
        System.out.println(d.longestPalindrome(str));
    }
}
