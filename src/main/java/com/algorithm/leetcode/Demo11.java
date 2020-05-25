package com.algorithm.leetcode;

import java.util.*;

/**
 * 给定一个字符串和一个单词的dict集合，在字符串中加入空格，组成一句话，并且每个单词在dict
 * 中都是有效词
 */
public class Demo11 {

    private static int count=0;
    private static Map<String,String> map = new HashMap<String,String>();
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> list = new ArrayList<String>();
        dfs(s,0,"",dict,list);
        return list;
    }

    private void dfs(String s, int begin, String targetStr, Set<String> dict, ArrayList<String> list) {
        count++;
        if(begin==s.length()){
            list.add(targetStr);
        }

        for (int i=begin;i<=s.length();i++){
            System.out.println(s.substring(begin,i));
            if (dict.contains(s.substring(begin,i))) {
                dfs(s,i, targetStr+" "+s.substring(begin,i), dict,list);
            }
        }
    }

    public ArrayList<String> dfsList(String str,Set<String> dict,Map<String,ArrayList<String>> map){
        ArrayList<String> lists = new ArrayList<String>();
        if (map.containsKey(str)) {
            return map.get(str);
        }
        for(int i=0;i<str.length();i++){
            String sub = str.substring(0,i);
            if (dict.contains(sub)) {
                String ss = str.substring(i,str.length());
                ArrayList<String> list = dfsList(ss,dict,map);
                if (lists.size()==0) {
                    continue;
                }else{
                    for (int j = 0; j < list.size(); j++) {
                    }
                }
            }
        }
        map.put(str,lists);
        return lists;
    }

    public static void main(String[] args) {
        String str = "catsanddog";
        Set<String> set = new HashSet<String>();
        set.add("cat");
        set.add("cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        Demo11 d = new Demo11();
        d.wordBreak(str,set);
        System.out.println(count);

    }
}
