package com.algorithm.leetcode_china.sort;

import java.util.*;

/**
 * 合并区间
 */
public class Demo56 {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        LinkedList<int[]> result = new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = intervals[i];
            if (result.getLast()[1] < temp[0]) {
                result.add(intervals[i]);
            } else {
                int a = Math.max(result.getLast()[1], temp[1]);
                result.getLast()[1] = a;
            }
        }

        return result.toArray(new int[result.size()][result.size()]);
    }

    public static void main(String[] args) {
        Demo56 demo = new Demo56();
        int[][] a = new int[][]{{74, 61, 46, 51, 50, 60}, {78, 63, 50, 54, 50, 64}};
        a = demo.merge(a);
    }
}
