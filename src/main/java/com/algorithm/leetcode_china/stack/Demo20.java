package com.algorithm.leetcode_china.stack;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 */
public class Demo20 {

    private Map<Character,Character> map = new HashMap<>();

    public Demo20(){
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char str = chars[i];
            if (stack.isEmpty()) {
                stack.push(str);
            }else{
                char temp = stack.peek();
                if (temp==map.get(str)) {
                    stack.pop();
                }else{
                    stack.push(str);
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

    }
}
