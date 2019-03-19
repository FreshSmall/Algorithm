package com.algorithm;

import java.util.Arrays;

/**
 * 数组中重复的数字
 */
public class Demo50 {


    public boolean duplicate(int numbers[],int length,int [] duplication) {
        Arrays.sort(numbers);
        for(int i=0;i<length-1;i++){
            if(numbers[i]==numbers[i+1]){
                duplication[0]=numbers[i];
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {


    }
}
