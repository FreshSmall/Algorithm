package com.algorithm.sword_offer;

import java.util.ArrayList;

/**
 * 输入n个数，找出最小的K个数
 * 用一个为K的容器来存放数字
 */
public class Demo29 {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        /**
         * 数组为null，数组长度小于k，或者k的长度为0，全部返回为null
         */
        if(input==null||input.length<k||k==0){
            return list;
        }
        for(int i =0;i<k;i++){
            list.add(input[i]);
        }
        for(int i=k;i<input.length;i++){
            int j = getMAX(list);
            if(list.get(j)>input[i]){
                list.set(j,input[i]);
            }
        }
        return list;
    }

    public int getMAX(ArrayList<Integer> list){
        int temp = 0;
        for(int i=0;i<list.size();i++){
            if(list.get(i)>list.get(temp)){
                temp = i;
            }
        }
        return temp;

    }

    public static void main(String[] args) {

    }
}
