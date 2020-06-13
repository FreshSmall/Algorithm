package com.algorithm.leetcode_china.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * 课程表
 *
 * @Author : yinchao
 * @create 2020/6/11 9:52 上午
 */
public class Demo207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> path = new ArrayList<>();
        int[] flag = new int[numCourses];
        for (int i = 0; i < numCourses; i++)
            path.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites) {
            path.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(path, flag, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> path, int[] flag, int i) {
        if (flag[i] == 1) {
            return false;
        }

        if (flag[i] == -1) {
            return true;
        }


        flag[i] = 1;
        for (int j = 0; j < path.get(i).size(); j++) {
            if (!dfs(path, flag, j)) {
                return false;
            }
        }
        flag[i] = -1;
        return true;
    }

    public static void main(String[] args) {
        Demo207 d = new Demo207();
        System.out.println(d.canFinish(2, new int[][]{{0, 1}}));
    }


}
