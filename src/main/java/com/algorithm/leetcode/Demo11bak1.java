package com.algorithm.leetcode;

import java.util.*;

public class Demo11bak1 {

    static int count = 0;

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        return dfs(s, dict, map);

    }

    public ArrayList<String> dfs(String s, Set<String> dict, HashMap<String, List<String>> map) {
        count++;
        if (map.containsKey(s))
            return (ArrayList<String>) map.get(s);

        ArrayList<String> lists = new ArrayList();
        if (s.equals(""))
            lists.add("");
        else {
            int len = s.length();
            for (int i = 1; i <= len; i++) {
                String sub = s.substring(0, i);
                if (dict.contains(sub)) {
                    ArrayList t = dfs(s.substring(i, len), dict, map);
                    if (t.size() == 0) {
                        continue;
                    } else {
                        for (int j = 0; j < t.size(); j++) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(sub).append(" ").append(t.get(j));
                            lists.add(sb.toString().trim());
                        }
                    }
                }
            }
        }
        map.put(s, lists);
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
        Demo11bak1 d = new Demo11bak1();
        List<String> list = d.wordBreak(str, set);
        System.out.println(count);
    }
}
