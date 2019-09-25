package com.algorithm.leetcode_china.linkedList;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 字符串解码
 */
public class Demo394 {

    public String decodeString1(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }

    public String decodeString(String s) {
        /*Stack<Integer> stack_multi = new Stack<>();
        Stack<String> stack_res = new Stack<>();
        StringBuffer sb = new StringBuffer();
        int number = 0;
        for (Character c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                number = number * 10 + Integer.parseInt(String.valueOf(c));
            } else if (c == '[') {
                stack_multi.add(number);
                number = 0;
                stack_res.add(sb.toString());
                sb = new StringBuffer();
            } else if (c == ']') {
                StringBuffer temp = new StringBuffer();
                int multi = stack_multi.pop();
                for (int i = 0; i < multi; i++) {
                    temp.append(sb.toString());
                }
                sb = new StringBuffer(stack_res.pop() + temp);
            } else {
                sb.append(c);
            }

        }
        return sb.toString();*/
        Stack<Integer> stack_multi = new Stack<Integer>();
        Stack<String>  stack_rec = new Stack<String>();
        StringBuffer sb = new StringBuffer();
        int number = 0;
        for(Character c:s.toCharArray()){
            if(c>='0'&&c<='9'){
                number = number*10+Integer.valueOf(String.valueOf(c));
            }else if(c=='['){
                stack_multi.add(number);
                number = 0;
                stack_rec.add(sb.toString());
                sb = new StringBuffer();
            }else if(c==']'){
                StringBuffer temp = new StringBuffer();
                int count = stack_multi.pop();
                for(int i=0;i<count;i++){
                    temp.append(sb.toString());
                }
                sb = new StringBuffer(stack_rec.pop()+temp.toString());
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Demo394 d = new Demo394();
        String str = "3[a]2[bc]";
        System.out.println(d.decodeString(str));
    }
}
