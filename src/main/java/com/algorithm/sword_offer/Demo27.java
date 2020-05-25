package com.algorithm.sword_offer;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 字符串全排列
 */
public class Demo27 {

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> arrarList = new ArrayList<String>();
        char temp;
        for(int i=0;i<str.length();i++){
            char[] chars = str.toCharArray();
            temp = chars[0];
            chars[0] = chars[i];
            chars[i] = temp;
            char temp1;
            for(int j = 0;j<chars.length;j++){
                temp1 = chars[0];
                chars[0] = chars[j];
                chars[j] = temp1;
                arrarList.add(new String(chars));
            }
        }
        return arrarList;
    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<String>();
        ArrayList<String> arrarList = new ArrayList<String>();
        String str = "a";
        char temp;
        for(int i=0;i<str.length();i++){
            char[] chars = str.toCharArray();
            temp = chars[0];
            chars[0] = chars[i];
            chars[i] = temp;
            char temp1;
            for(int j = 0;j<chars.length-1;j++){
                temp1 = chars[j];
                chars[j] = chars[j+1];
                chars[j+1] = temp1;
                set.add(new String(chars));
                System.out.println(new String(chars));
            }
        }

        arrarList = new ArrayList<>(set);
        for(int i=0;i<arrarList.size();i++){
//            System.out.println(arrarList.get(i));
        }


    }

}
