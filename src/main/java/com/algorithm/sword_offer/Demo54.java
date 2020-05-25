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
            chars[ch]= ++index;
        }else{
            chars[ch]=-1;
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        char str = '#';
        int temp = Integer.MAX_VALUE;
        for(int i =0;i<256;i++){
            if(chars[i]>0&&chars[i]<temp){
                str = (char)i;
                temp = chars[i];
            }
        }
        return str;
    }

    public static void main(String[] args) {
        Demo54 d = new Demo54();
        d.Insert('g');
        System.out.println(d.FirstAppearingOnce());
    }
}
