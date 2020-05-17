package com.algorithm.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/17 3:08 下午
 */
public class ShiMo {


    public List<int[][]> polymerize(int[][] input) {
        Arrays.sort(input, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                if (x[0] < y[0]) {
                    return -1;
                } else if (x[0] > y[0]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        List<int[][]> listtotal = new ArrayList<>();
        List<int[][]> list = new ArrayList<>();
        boolean[] flag = new boolean[input.length];
        for (int i = 0; i < input.length; i++) {
            if (!flag[i]) {
                int[][] b = {{input[i][0], input[i][1]}};
                list.add(b);
            } else {
                continue;
            }

            for (int j = i + 1; j < input.length; j++) {
                if (listContains(list, input[j][0], input[j][1])) {
                    flag[j] = true;
                    int[][] a = {{input[j][0], input[j][1]}};
                    list.add(a);
                }
            }
            if (!list.isEmpty()) {
                listtotal.add(listToArray(list));
            }
            list = new ArrayList<>();
        }
        return listtotal;
    }

    private boolean listContains(List<int[][]> list, int len, int len2) {
        for (int i = 0; i < list.size(); i++) {
            int[][] a = list.get(i);
            if ((a[0][0] == len && Math.abs(a[0][1] - len2) == 1) || (a[0][1] == len2) && Math.abs(a[0][0] - len) == 1) {
                return true;
            }
        }
        return false;
    }

    private int[][] listToArray(List<int[][]> list) {
        int[][] a = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            a[i][0] = list.get(i)[0][0];
            a[i][1] = list.get(i)[0][1];
        }
        return a;
    }

    public static void main(String[] args) {
        int[][] input = {
                {1, 1},
                {2, 1},
                {2, 4},
                {2, 5},
                {4, 4},
                {3, 2},
                {3, 4},
                {4, 5},
                {4, 6},
                {4, 7},
                {5, 3},
                {6, 3},
                {6, 4},
                {6, 5}

        };

        ShiMo t = new ShiMo();
        t.polymerize(input);


        for(int[] integers : input){
            System.out.println(Arrays.toString(integers));
        }


    }


}
