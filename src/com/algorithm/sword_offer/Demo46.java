package com.algorithm.sword_offer;

/**
 * 圆圈中最后剩下的数字
 */
public class Demo46 {

    public int LastRemaining_Solution(int n, int m) {
        if(n<1||m<1){
            return -1;
        }
        int last =0;
        for(int i=0;i<=n;i++){
            last = (last+m)%i;
        }
        return last;
    }

    public static void main(String[] args) {

    }
}
