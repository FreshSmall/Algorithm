package com.algorithm.leetcode_china.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * 实现计算器
 *
 * @Author : yinchao
 * @create 2020/5/15 11:00 下午
 */
public class Demoex {

    //todo 45+88/4-2*26

    public static int cal(String str) {
        Stack<Integer> res = new Stack<Integer>();
        LinkedList<String> stacknum = new LinkedList<String>();
        Stack<String> stackflag = new Stack<String>();
        int number = 0;
        for (Character c : str.toCharArray()) {
            if (c == '+' || c == '-') {
                stacknum.push(String.valueOf(number));
                number = 0;
                while (!stackflag.isEmpty()) {
                    String string = stackflag.peek();
                    if ("*".equals(string) || "/".equals(string)) {
                        stacknum.push(stackflag.pop());
                    }else{
                        break;
                    }
                }
                stackflag.push(String.valueOf(c));

            } else if (c == '*' || c == '/') {
                stacknum.push(String.valueOf(number));
                number = 0;
                stackflag.push(String.valueOf(c));
            } else {
                number = 10 * number + Integer.valueOf(String.valueOf(c));
            }
        }
        stacknum.push(String.valueOf(number));
        while(!stackflag.isEmpty()){
            stacknum.push(stackflag.pop());
        }

        while (!stacknum.isEmpty()) {
            String arg = stacknum.getLast();
            if(arg.equals("+")||arg.equals("-")||arg.equals("*")||arg.equals("/")){
                int a = res.pop();
                int b = res.pop();
                switch (arg){
                    case "+":
                        res.push(b+a);
                        break;
                    case "-":
                        res.push(b-a);
                        break;
                    case "*":
                        res.push(b*a);
                        break;
                    case "/":
                        res.push(b/a);
                        break;

                }
                stacknum.removeLast();
            }else{
                res.push(Integer.valueOf(stacknum.removeLast()));
            }
        }
        return res.pop();
    }

    public static void main(String[] args) {
        String str = "45+88/4-2*26*1";
        System.out.println(cal(str));
    }
}
