/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.algorithm.daily;

/**
 * @author yinchao
 * @description 有效数字
 * @team wuhan operational dev.
 * @date 2021/6/17 11:13 下午
 **/
public class Demo_2021_6_17 {

    public static boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        int ecount = 0;
        int dcount = 0;
        int fcount = 0;
        if (chars.length == 1) {
            return chars[0] >= '0' && chars[0] <= '9';
        }
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((c >= 'a' && c <= 'z' && c != 'e') || (c >= 'A' && c <= 'Z' && c != 'E')) {
                return false;
            } else if (c == 'e' || c == 'E') {
                ecount++;
                if (i == 0) {
                    return false;
                }
                if (i == chars.length - 2) {
                    c = chars[++i];
                    if (c > '9' || c < '0') {
                        return false;
                    }
                } else if (i < chars.length - 2) {
                    c = chars[++i];
                    if (c == '+' || c == '-') {
                        c = chars[++i];
                        if (c > '9' || c < '0') {
                            return false;
                        }
                    } else if (c > '9' || c < '0') {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (c == '+' || c == '-') {
                if (i > 0 && (chars[i - 1] != 'e' || chars[i - 1] != 'E')) {
                    return false;
                }
                fcount++;
                if (i < chars.length - 1) {
                    c = chars[++i];
                    if ((c > '9' || c < '0') && c != '.') {
                        return false;
                    }
                } else {
                    return false;
                }
            } else if (c == '.') {
                dcount++;
                if (i < chars.length - 1) {
                    c = chars[++i];
                    if (c > '9' || c < '0') {
                        return false;
                    }
                }
            }
        }
        if (ecount > 1 || dcount > 1 || fcount > 1) {
            return false;
        }
        return true;
    }
}
