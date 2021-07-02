package com.algorithm.leetcode_china.dp;

import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA. 最长回文字符串
 *
 * @Author : yinchao
 * @create 2020/5/14 11:40 下午
 */
public class Demo5 {

    public String longestPalindrome(String s) {
        int len = s.length();
        int start = 0;
        int maxLen = 0;
        for (int i = 0; i < len; i++) {
            // 回文字符串奇数还是偶数
            int cur = Math.max(getMaxLen(i, i, len, s), getMaxLen(i, i + 1, len, s));
            if (cur > maxLen) {
                maxLen = cur;
                start = i - (maxLen - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    public int getMaxLen(int l, int r, int len, String s) {
        while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
            --l;
            ++r;
        }
        return r - l - 1;
    }

    public String longestPalindrome1(String s) {
        int len = s.length();
        int maxLen = 1;
        int begin = 0;
        boolean[][] flag = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            flag[i][i] = true;
        }
        char[] chars = s.toCharArray();
        for (int L = 2; L <= len; L++) {
            for (int i = 0; i < len; i++) {
                int j = i + L - 1;
                if (j > len - 1) {
                    break;
                }
                if (chars[i] == chars[j]) {
                    if ((j - i) < 3) {
                        flag[i][j] = true;
                    } else {
                        flag[i][j] = flag[i + 1][j - 1];
                    }
                } else {
                    flag[i][j] = false;
                }
                if (j - i + 1 > maxLen && flag[i][j]) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    public static void main(String[] args) {
        String str = "aaaa";
        Demo5 d = new Demo5();
        System.out.println(d.longestPalindrome1(str));
    }
}
