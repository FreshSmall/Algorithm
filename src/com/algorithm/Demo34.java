package com.algorithm;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查找第一个出现一次字符的位置
 */
public class Demo34 {
    public int FirstNotRepeatingChar(String str) {
        char[] strs = str.toCharArray();
        Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < strs.length; i++) {
            Integer a = map.get(strs[i]);
            if (a == null || a == 0) {
                map.put(strs[i], 1);
            } else {
                map.put(strs[i], ++a);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return str.indexOf(entry.getKey());
            }
        }
        return -1;
    }

    public int FirstNotRepeatingChar1(String str) {
        char[] index = str.toCharArray();
        int[] a = new int['z'+1];
        for(int i=0;i<index.length;i++){
            a[(int)index[i]]++;
        }
        for(int i=0;i<a.length;i++){
            if(a[i]==1){
                return str.indexOf(i)+1;
            }
        }
        return -1;

    }

    public int FirstNotRepeatingChar2(String str) {
        Map<Character,Integer> map = new LinkedHashMap<Character,Integer>();
        char[] chars = str.toCharArray();
        for(int i=0;i<chars.length;i++){
            Integer a = map.get(chars[i]);
            if(a!=null&&a>0){
                map.put(chars[i],a+1);
            }else{
                map.put(chars[i],1);
            }
        }

        for(Map.Entry<Character, Integer> entry:map.entrySet()){
            if(entry.getValue()==1){
                System.out.println(entry.getKey());
                System.out.println(str.indexOf('l'));
                return str.indexOf(entry.getKey());
            }
        }

        return -1;

    }




    public static void main(String[] args) {
        String str = "google";
        Demo34 dd = new Demo34();
        int a = dd.FirstNotRepeatingChar2(str);
        System.out.println(a);
    }
}
