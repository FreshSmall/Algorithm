package com.algorithm;

/**
 * 翻转字符串
 */
public class Demo44 {

    public String ReverseSentence(String str) {
        if(str==null){
            return "";
        }else if("".equals(str.trim())){
            return str;
        }else{
            String[] strs = str.split(" ");
            return reverse(strs,0,strs.length-1);
        }
    }

    public String reverse(String[] str,int start,int end){

        String temp;
        while(start<end){
            temp  = str[start];
            str[start] = str[end];
            str[end] = temp;
            start++;
            end--;
        }
        StringBuffer sb =new StringBuffer();
        for(int i =0;i<str.length;i++){
            if(i==str.length-1){
                sb.append(str[i]);
            }else{
                sb.append(str[i]+" ");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String str = " ";
        Demo44 d = new Demo44();
        System.out.println(d.ReverseSentence(str));
    }
}
