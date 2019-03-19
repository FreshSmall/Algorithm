package com.leetcode;

import java.util.*;

/**
 * 给定一个字符串和一个单词的dict集合，在字符串中加入空格，组成一句话，并且每个单词在dict
 * 中都是有效词
 */
public class Demo11bak {

    int count =0;
    public ArrayList<String> list = new ArrayList<>();
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        dfs(s, s.length(), "", dict);
        System.out.println(count);
        return list;
    }

    private void dfs(String s, int index, String str, Set<String> dict) {
        count++;
        if (index <= 0) {
            if (str.length() > 0) {
                list.add(str.substring(0, str.length() - 1));
            }
        }
        for (int i = index; i >= 0; i--) {
            if (dict.contains(s.substring(i, index))) {
                dfs(s, i, s.substring(i, index) + " " + str, dict);
            }
        }
    }




    public static void main(String[] args) {
        String str = "catsanddog";
        Set<String> set = new HashSet<String>();
        set.add("cat");
        set.add("cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        Demo11bak d = new Demo11bak();
        d.wordBreak(str,set);
    }
}
