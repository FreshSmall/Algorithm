package com.algorithm.sword_offer;

/**
 * 字符流中第一个不重复的字符
 */
public class Demo54 {

    private int[] chars = new int[256];
    int index = 0;
    public void Insert(char ch)
    {
        if(chars[ch]==0){
            chars[ch]= index++;
        }else{
            chars[ch]=-1;
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char str = '#';
        int temp = Integer.MAX_VALUE;
        for(int i =0;i<chars.length;i++){
            if(chars[i]>0&&chars[i]<temp){
                str = (char)(i-48);
                temp = chars[i];
            }
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
