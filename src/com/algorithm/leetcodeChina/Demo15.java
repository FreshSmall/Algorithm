package com.algorithm.leetcodeChina;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class Demo15 {

    public List<List<Integer>> threeSum(int[] nums) {

        ArrayList<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }


        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (start < end) {
                List<Integer> path = new ArrayList<>();
                int a = nums[i] + nums[start] + nums[end];
                if (a == 0) {
                    path.add(nums[i]);
                    path.add(nums[start]);
                    path.add(nums[end]);
                    list.add(path);
                    while (start < end && nums[end] == nums[--end]) {

                    }
                    while (start < end && nums[start] == nums[++start]) {

                    }

                } else if (a > 0) {
                    while (start < end && nums[end] == nums[--end]) {

                    }
                } else if (a < 0) {
                    while (start < end && nums[start] == nums[++start]) {

                    }
                }


            }

        }
        return list;

    }

    public static void main(String[] args) {
        Demo15 d = new Demo15();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List list = d.threeSum(nums);
        System.out.println(list.size());

    }

}
