package com.algorithm.leetcode_china.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 根据身高重建队列
 *
 * @Author : yinchao
 * @create 2020/5/26 9:53 下午
 */
public class Demo406 {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });
        List<int[]> list = new LinkedList<>();
        for (int i = 0; i < people.length; i++) {
            int[] p = people[i];
            list.add(p[1],p);
        }
        return list.toArray(new int[people.length][2]);

    }

    public static void main(String[] args) {
        int[][] people = new int[][]{{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        Demo406 d = new Demo406();
        people = d.reconstructQueue(people);
    }
}
