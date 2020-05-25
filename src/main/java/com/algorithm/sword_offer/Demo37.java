package com.algorithm.sword_offer;

/**
 * 统计一个数字在排序数组中出现的数字
 */
public class Demo37 {

    /*public int GetNumberOfK(int[] array, int k) {
        int count = 0;
        if (array != null && array.length > 0) {
            int first = getNumberFirst(array, 0, array.length - 1, k);
            int last = getNumberLast(array, 0, array.length - 1, k);
            if (first > -1 && last > -1) {
                count = last - first + 1;
            }
        }
        return count;
    }

    public int getNumberFirst(int[] array, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) >> 1;
        int middleData = array[middle];
        if (middleData == k) {
            if ((middle > 0 && array[middle - 1] != k) || middle == 0) {
                return middle;
            } else {
                end = middle - 1;
            }
        } else if (middleData > k) {
            end = middle - 1;
        } else {
            start = middle + 1;
        }
        return getNumberFirst(array, start, end, k);
    }

    public int getNumberLast(int[] array, int start, int end, int k) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) >> 1;
        int middleData = array[middle];
        if (middleData == k) {
            if ((middle < array.length-1 && array[middle + 1] != k) || middle == array.length-1) {
                return middle;
            } else {
                start = middle + 1;
            }
        } else if (middleData < k) {
            start = middle + 1;
        } else {
            end = middle - 1;
        }
        return getNumberLast(array, start, end, k);
    }*/

    public int GetNumberOfK(int [] array , int k) {
        if(array==null||array.length==0){
            return 0;
        }
        int first = getFirstIndex(array,0,array.length-1,k);
        int last = getLastIndex(array,0,array.length-1,k);
        if (first > -1 && last > -1) {
            return last - first + 1;
        }
        return 0;
    }

    public int getFirstIndex(int[] array,int low,int high,int k){
        if(low>high){
            return -1;
        }
        int middle = (low+high)>>1;
        if(array[middle]==k){
            if((middle>0&&array[middle-1]<k)||middle==0){
                return middle;
            }else{
                high = middle-1;
            }
        }else if(array[middle]<k){
            low = middle;
        }else{
            high = middle;
        }
        return getFirstIndex(array,low,high,k);
    }

    public int getLastIndex(int[] array,int low,int high,int k){
        if(low>high){
            return -1;
        }
        int middle = (low+high)>>1;
        if(array[middle]==k){
            if((middle<array.length-1&&array[middle+1]>k)||middle==array.length-1){
                return middle;
            }else{
                low = middle+1;
            }
        }else if(array[middle]<k){
            low = middle;
        }else{
            high = middle;
        }
        return getLastIndex(array,low,high,k);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3, 3, 3};
        Demo37 demo37 = new Demo37();
        System.out.println(demo37.GetNumberOfK(a, 3));
    }
}
