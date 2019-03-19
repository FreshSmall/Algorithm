package com.leetcode;

import java.util.Stack;

/**
 * 将一系列的字符串用四则运算计算，计算出数值
 */
public class Demo2 {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<tokens.length;i++){
            if(tokens[i]=="+"||tokens[i]=="-"||
                    tokens[i]=="*"||tokens[i]=="/"){
                int b = stack.pop();
                int a = stack.pop();
                stack.push(callNum(tokens[i],a,b));
            }else{
                stack.push(Integer.valueOf(tokens[i]));
            }
        }
        return stack.pop();
    }

    public int callNum(String expression,int a,int b){
        switch(expression){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
        }
        return 0;
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();
        String[] tokens = {"0","3","/"};
        System.out.println(d.evalRPN(tokens));
    }
}
