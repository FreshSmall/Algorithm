package com.algorithm.back_track;

/**
 * 背包问题（回溯法）
 */
public class BackpackDemo {

    private int backpack_contain = 32;//背包重量
    private int good_num = 3;//对象数目
    private int[] goods = {20, 15, 16};//对象重量数组
    private int[] values = {40, 25, 26};//对象价值数组
    private int backpack_current_weight = 0;//当前背包重量
    private int backpack_current_values = 0;//当前背包价值
    private int bestValues;//当前最大收益
    private int[] path = new int[3];//记录在树中的移动路径，1：选择该组数据，0：不选择该组数据

    /**
     * 回溯法
     *
     * @param i 对象数量
     */
    public void getBestValuesRecursive(int i) {

        if (i >= good_num) {
            if (backpack_current_values > bestValues) {
                bestValues = backpack_current_values;
            }
            return;
        }

        if (backpack_current_weight + goods[i] < backpack_contain) {
            path[i] = 1;
            backpack_current_weight += goods[i];
            backpack_current_values += values[i];
            getBestValuesRecursive(i + 1);
            //下面开始进行回溯
            backpack_current_weight -= goods[i];
            backpack_current_values -= values[i];
        }
        path[i] = 0;
        getBestValuesRecursive(i + 1);
    }

    /**
     * 递归思路
     *
     * @param num
     * @param reContainNum
     */
    public int makeRecursive(int num, int reContainNum) {
        int r1 = 0;
        int r2 = 0;
        int r = 0;
        if (num == -1) {
            return 0;
        }
        if (reContainNum >= values[num]) {//背包中剩余容量
            r1 = makeRecursive(num - 1, reContainNum);
            r2 = makeRecursive(num - 1, reContainNum - values[num]) + values[num];
            r = r2 > r1 ? r2 : r1;
        }
        return r;
    }

    /**
     * 非递归思路
     */
    public void makeNotRecursive() {
        for (int i = 0; i < good_num; i++) {
            for (int j = backpack_contain; j >= values[good_num]; j--) {

            }
        }
    }


    public static void main(String[] args) {
        BackpackDemo backpackDemo = new BackpackDemo();
        backpackDemo.getBestValuesRecursive(0);
        System.out.println(backpackDemo.bestValues);
    }
}
