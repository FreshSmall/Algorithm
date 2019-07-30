package com.algorithm;

/**
 * 数组中出现超过一半次数的数字
 */
public class Demo28 {


    public static int MoreThanHalfNum_Solution(int [] array) {
        if(array==null||array.length==0){
            return 0;
        }

        int result = array[0];
        int time = 1;
        for (int i=1;i<array.length;i++){
            if(time==0){
                result=array[i];
                time=1;
            }else if(result==array[i]){
                time++;
            }else{
                time--;
            }
        }

        time =0;
        for(int i=0;i<array.length;i++){
            if(array[i]==result){
                time++;
            }
        }
        if(time<=array.length/2){
            return 0;
        }
        return  result;

    }

    public static void main(String[] args) {

        int[] array={4,2,4,1,4,2};
        int a = MoreThanHalfNum_Solution(array);
        System.out.println(a);
    }

}
