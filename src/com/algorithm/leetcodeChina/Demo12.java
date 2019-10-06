package com.algorithm.leetcodeChina;

/**
 * 整数转罗马数字
 * 确保输入在1到3999内
 */
public class Demo12 {

    public String intToRoman(int num) {
        StringBuffer sb = new StringBuffer();
        int a = num/1000;
        for(int i=0;i<a;i++){
            sb.append("M");
        }
        int b = (num%1000)/100;
        if(b==5){
            sb.append("D");
        }else if(b==9){
            sb.append("CM");
        }else if(b==4){
            sb.append("CD");
        }else if(b>5){
            sb.append("D");
            for(int i=0;i<b-5;i++){
                sb.append("C");
            }
        }else{
            for(int i=0;i<b;i++){
                sb.append("C");
            }
        }

        int c = (num%100)/10;
        if(c==5){
            sb.append("L");
        }else if(c==9){
            sb.append("XC");
        }else if(c==4){
            sb.append("XL");
        }else if(c>5){
            sb.append("L");
            for(int i=0;i<c-5;i++){
                sb.append("X");
            }
        }else{
            for(int i=0;i<c;i++){
                sb.append("X");
            }
        }

        int d  =(num%10);
        if(d==5){
            sb.append("V");
        }else if(d==9){
            sb.append("IX");
        }else if(d==4){
            sb.append("IV");
        }else if(d>5){
            sb.append("V");
            for(int i=0;i<d-5;i++){
                sb.append("I");
            }
        }else{
            for(int i=0;i<d;i++){
                sb.append("I");
            }
        }
        return sb.toString();
    }
}
