package com.algorithm;

import java.util.ArrayList;

/**
 * 和为S的两个数
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的
 */
public class Demo42 {

    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(array==null||array.length<2){
            return arrayList;
        }
        int i=0;
        int j=array.length-1;
        int small = array[i];
        int big = array[j];
        int curSum = small+big;
        while(small<big){
            if(curSum==sum){
                if(arrayList!=null&&arrayList.size()>0){
                    if((small*big)<(arrayList.get(0)*arrayList.get(1))){
                        arrayList.clear();
                        arrayList.add(small);
                        arrayList.add(big);
                    }
                }else{
                    arrayList.add(small);
                    arrayList.add(big);
                }
                curSum-=small;
                i++;
                small=array[i];
                curSum+=small;
            }else if(curSum>sum){
                curSum-=big;
                j--;
                big=array[j];
                curSum+=big;
            }else{
                curSum-=small;
                i++;
                small=array[i];
                curSum+=small;
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        Demo42 d = new Demo42();
        int[] a={1,2,4,7,11,15};
        ArrayList<Integer> arrayList = d.FindNumbersWithSum(a,15);
        System.out.println(arrayList.size());
    }
}
