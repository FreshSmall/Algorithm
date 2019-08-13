package com.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 动态规划,将字符串拆分成多个字符
 */
public class Demo12 {

    public boolean wordBreak(String s, Set<String> dict) {
        Boolean[] b= new Boolean[s.length()+1];
        b[0]=true;
        for(int i=1;i<s.length()+1;i++){
            for(int j=0;j<i;j++){
                if(b[j]&& dict.contains(s.substring(j,i))){
                    b[i]=true;
                    break;
                }else{
                    b[i]=false;
                }
            }
        }
        return b[s.length()];
    }

    public static void main(String[] args) {

        String s = "leetcode";
        Set<String> set = new HashSet<>();
        set.add("lee");
        set.add("tcode");
        Demo12 d = new Demo12();
        System.out.println(d.wordBreak(s,set));

        System.out.println(s.substring(0,1));

    }
}
