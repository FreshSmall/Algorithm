package com.algorithm.leetcode_china.greedy;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * 任务调度器
 *
 * @Author : yinchao
 * @create 2020/6/1 9:55 上午
 */
public class Demo621 {

    public int leastInterval(char[] tasks, int n) {
        int[] map = new int[26];
        for (char task : tasks) {
            map[task - 'A']++;
        }
        Arrays.sort(map);//依次排序，将任务种类最多的顺序进行排列
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0) {//最多的任务执行完成，重新排列再按照原来逻辑完成
                    break;
                }
                if (i < 26 && map[25 - i] > 0) {//在执行A任务的空隙，执行其他的任务
                    map[25 - i]--;
                }
                //任务执行时间增加
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    public static void main(String[] args) {
        Demo621 d = new Demo621();
        char[] tasks = new char[]{'A','A','B','B','A','B'};
        System.out.println(d.leastInterval(tasks,2));
    }

}
