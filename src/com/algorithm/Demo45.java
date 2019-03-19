package com.algorithm;

import java.util.Arrays;

/**
 * 扑克牌中的顺子，大小王可以看做是任何数
 */
public class Demo45 {

    public boolean isContinuous(int [] numbers) {
        int numLength = numbers.length;
        if(numbers==null||numLength==0){
            return false;
        }
        int zeroNum = 0;
        int numberInterval = 0;
        Arrays.sort(numbers);
        for(int i =0;i<numLength-1;i++){
            if(numbers[i]==0){
                zeroNum++;
                continue;
            }else if(numbers[i]==numbers[i+1]){
                return false;
            }
            numberInterval+=numbers[i+1]-numbers[i]-1;
        }
        if(zeroNum>=numberInterval){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a ={0,2,3,5,7};
        Demo45 d = new Demo45();
        System.out.println(d.isContinuous(a));
    }
}
