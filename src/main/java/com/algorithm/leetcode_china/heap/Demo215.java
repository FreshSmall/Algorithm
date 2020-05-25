package com.algorithm.leetcode_china.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 */
public class Demo215 {

    public int findKthLargest(int[] nums,int k){
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a-b;
            }
        });
        for(int i: nums){
            queue.add(i);
            if(queue.size()>k){
                queue.poll();
            }
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        int[] a = {3,2,1,5,6,4};
        Demo215 d = new Demo215();
        int number = d.findKthLargest(a,2);
        System.out.println(number);
    }
}
