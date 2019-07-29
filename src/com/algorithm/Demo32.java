package com.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 把数组排成最小的数
 */
public class Demo32 {

    public String PrintMinNumber(int[] numbers) {
        String str = "";
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            list.add(numbers[i]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer num1, Integer num2) {
                String s1 = num1 + "" + num2;
                String s2 = num2 + "" + num1;
                return s1.compareTo(s2);
            }
        });

        for (int i = 0; i < list.size(); i++) {
            str += list.get(i) + "";
        }
        return str;
    }

    public static void main(String[] args) {

    }
}
