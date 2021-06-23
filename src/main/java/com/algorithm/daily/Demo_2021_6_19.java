/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.algorithm.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/6/19 9:40 下午
 **/
public class Demo_2021_6_19 {

    String sumStr = "";
    int sumLength = 0;
    int targetSum = 0;
    int count_maxLength = 0;
    int count_dfs = 0;

    public int maxLength(List<String> arr) {
        dfsMaxLength(0, arr);
        DFS(0, arr);
        return targetSum;
    }

    public void dfsMaxLength(int startIndex, List<String> arr) {
        count_maxLength++;
        if (startIndex == arr.size()) {
            sumLength = Math.max(sumLength, sumStr.length());
            if (sumLength > targetSum) {
                targetSum = sumLength;
            }
            return;
        }
        for (int i = startIndex; i < arr.size(); i++) {
            boolean flag = false;
            String str = arr.get(i);
            // 已经计算过
            if (sumStr.contains(str)) {
                return;
            }
            // 查看是否能添加
            char[] chars = str.toCharArray();
            Set<Character> sets = new HashSet<Character>();
            for (int j = 0; j < chars.length; j++) {
                if (sumStr.contains(String.valueOf(chars[j]))) {
                    flag = true;
                    break;
                }
                if (sets.contains(chars[j])) {
                    flag = true;
                    break;
                } else {
                    sets.add(chars[j]);
                }
            }
            if (!flag) {
                sumStr += str;
                sumLength += str.length();
                dfsMaxLength(i + 1, arr);
                sumStr = sumStr.replace(str, "");
                sumLength -= str.length();
            } else {
                dfsMaxLength(i + 1, arr);
            }
        }
    }


    StringBuilder curRes = new StringBuilder();
    Set<Character> set = new HashSet<>();
    int res = 0;

    public void DFS(int curIndex, List<String> arr) {//curIndex表示当前遍历的索引。
        count_dfs++;
        if (curIndex == arr.size()) {
            res = Math.max(res, curRes.length());
            return;
        }
        for (int i = curIndex; i < arr.size(); i++) {
            char[] curArr = arr.get(i).toCharArray();
            boolean canAdd = true;//标记当前字符串能否被添加到curRes中。
            int j;//记录当前字符串添加到set中的索引，用于后续从set中移除。
            for (j = 0; j < curArr.length; j++) {
                if (set.contains(curArr[j])) {
                    canAdd = false;
                    break;
                } else {
                    set.add(curArr[j]);
                }
            }
            if (canAdd) {
                //可以添加，进行相应的操作。
                curRes.append(curArr);
                DFS(i + 1, arr);
                //回溯curRes和set
                curRes.delete(curRes.length() - curArr.length, curRes.length());
                for (char c : curArr) {
                    set.remove(c);
                }
            } else {
                //不可以添加，但是之前我们向set中加入了一部分curArr中的字符，所以要移除这一部分。
                for (int k = 0; k < j; k++) {
                    set.remove(curArr[k]);
                }
                DFS(i + 1, arr);
            }
        }
    }


    public static void main(String[] args) {
        Demo_2021_6_19 demo = new Demo_2021_6_19();
        List<String> list = new ArrayList<>();
        list.add("cha");
        list.add("r");
        list.add("act");
        list.add("ers");
        list.add("bcd");
        System.out.println(demo.maxLength(list));
        System.out.println(demo.res);
        System.out.println(demo.count_maxLength);
        System.out.println(demo.count_dfs);
    }

}
