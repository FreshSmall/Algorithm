package com.algorithm;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 字符串的全排列
 */
public class Demo27Rec {


    public ArrayList<String> Permutation(String str) {
        ArrayList<String> arrarList = new ArrayList<String>();
        HashSet<String> set = new HashSet<String>();
        Permutation(str.toCharArray(),0,set);
        return new ArrayList(set);
    }

    public static void Permutation(char[] s, int from, HashSet<String> set) {
        if(s.length-1<0)
            return;
        if(from == s.length-1){
            System.out.println(s);
        }
        else{
            for(int i=from;i<=s.length-1;i++){
                swap(s,i,from);
                Permutation(s,from+1,set);
                swap(s,from,i);
            }
        }
    }

    public static void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }


    public static void main(String[] args) {
        String str = "a";
        char[] chars = str.toCharArray();
        int from = 0;
        int to = chars.length-1;
        Permutation(chars,from,null);
    }
}
