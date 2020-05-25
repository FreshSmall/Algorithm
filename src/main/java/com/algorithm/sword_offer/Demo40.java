package com.algorithm.sword_offer;

/**
 * 数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 * 思路：异或
 */
public class Demo40 {

    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array==null||array.length<2){
            return ;
        }
        int resultOR=0;
        for(int i=0;i<array.length;i++){
            resultOR^=array[i];
        }
        int indexOf1 = findFirstBitIs1(resultOR);
        for(int i=0;i<array.length;i++){
            if(isBit1(array[i],indexOf1)){
                num1[0]^=array[i];
            }else{
                num2[0]^=array[i];
            }
        }

    }

    private boolean isBit1(int num, int indexOf1) {
        num = num>>indexOf1;
        return (num&1)==1;
    }

    public int findFirstBitIs1(int num){
        int indexBit =0;
        while((num&1)==0){
            num=num>>1;
            indexBit++;
        }
        return indexBit;
    }

    public static void main(String[] args) {
        System.out.println(20^32);
        System.out.println(52&1);
        System.out.println(Integer.toBinaryString(20^32));
    }
}
