package com.algorithm.leetcodeChina;

/**
 * 盛水最多的容器
 */
public class Demo11 {

    public int maxArea(int[] height) {

        if (height == null || height.length == 1) {
            return 0;
        }
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int temp = (right - left) * Math.min(height[left], height[right]);
            if (temp > maxArea) {
                maxArea = temp;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;

    }
}
