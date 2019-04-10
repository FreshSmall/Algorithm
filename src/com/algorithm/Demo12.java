package com.algorithm;

/**
 * 数值的整数次方
 */
public class Demo12 {

    public static double Power(double base, int exponent) {
        double temp = 1.0;
        if(exponent ==0){
            return 1.0;
        }
        if(exponent>0){
            for(int i=1;i<=exponent;i++){
                temp = temp *base;
            }
        }else if(exponent<0){
            for(int i=exponent;i<=-1;i++){
                temp =temp *base;
            }
            temp=1/temp;
        }
        return temp;
    }

    public static void main(String[] args) {
        double base = 2;
        int exponent = -3;
        double temp = Power(base,exponent);
        System.out.println(temp);
    }
}
