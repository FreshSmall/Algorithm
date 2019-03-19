package com.algorithm;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 * 思路：先考虑两个数为序列的最大值和最小值，如果序列的和小于给定值，则扩大序列最大值，否则就扩大序列的最小值
 */
public class Demo41 {


    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if(sum<3){
            return arrayLists;
        }
        int middle = (1+sum)>>1;
        int small =1;
        int big = 2;
        int curSum = small+big;
        while (small<big&&small<middle){
            if(curSum==sum){
                addNumbers(arrayLists,small,big);
                big++;
                curSum+=big;
            }else if(curSum>sum){
                curSum-=small;
                small++;
            }else{
                big++;
                curSum+=big;
            }
        }
        return arrayLists;
    }

    private void addNumbers(ArrayList<ArrayList<Integer>> arrayLists, int small, int big) {
        ArrayList<Integer> array = new ArrayList<>();
        for(int i=small;i<=big;i++){
            array.add(i);
        }
        arrayLists.add(array);
    }

    public static void main(String[] args) {
        Demo41 d = new Demo41();
        long start = System.currentTimeMillis();
        ArrayList<ArrayList<Integer>> arrayLists = d.FindContinuousSequence(15);
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(arrayLists.size());
    }
}
