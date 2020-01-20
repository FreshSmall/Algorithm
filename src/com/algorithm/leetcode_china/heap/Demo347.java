package com.algorithm.leetcode_china.heap;

import java.util.*;

/**
 * 前K个高频元素
 */
public class Demo347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a)-map.get(b);
            }
        });

        for(int i:map.keySet()){
            queue.add(i);
            if(queue.size()>k){
                queue.poll();
            }
        }

        List<Integer> list = new ArrayList<>();
        while(!queue.isEmpty()){
            list.add(queue.poll());
        }
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        Demo347 demo = new Demo347();
        int[] nums = {1,1,1,2,2,3};
        demo.topKFrequent(nums,2);
    }
}
