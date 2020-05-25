package com.jvm;

import java.util.Stack;

public class TestGC {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) {
        /*byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[4*_1MB];
        allocation2 = new byte[4*_1MB];
        allocation3 = new byte[4*_1MB];
        allocation4 = new byte[4*_1MB];
        while (true){}*/

        Stack<Character> stack = new Stack<Character>();
        stack.push('C');
        System.out.println((char)(67));
        if(stack.contains((char)(67))){
            System.out.println("包含");
        }else{
            System.out.println("不包含");
        }
    }
}
