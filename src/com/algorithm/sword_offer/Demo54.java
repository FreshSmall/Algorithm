package com.algorithm.sword_offer;

/**
 * 字符流中第一个不重复的字符
 */
public class Demo54 {
    int[] count = new int[256];
    //Insert one char from stringstream
    int index = 1;

    public void Insert(char ch) {
        if (count[ch] == 0) {
            count[ch] = index++;
        } else {
            count[ch] = -1;
        }
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int temp = Integer.MAX_VALUE;
        char ch = '#';
        for (int i = 0; i < 256; i++) {
            if (count[i] != 0 && count[i] != -1 && count[i] < temp) {
                temp = count[i];
                ch = (char) i;
            }
        }
        return ch;
    }

    public static void main(String[] args) {
        Demo54 d = new Demo54();
        String str = "google";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            d.Insert(chars[i]);
        }
        System.out.println(d.FirstAppearingOnce());
    }
}
