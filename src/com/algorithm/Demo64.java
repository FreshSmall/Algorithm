package com.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 滑动窗口的最大值
 */
public class Demo64 {

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if(size == 0) return res;
        int begin;
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < num.length; i++){
            begin = i - size + 1;
            if(q.isEmpty())
                q.add(i);
            else if(begin > q.peekFirst())
                q.pollFirst();

            while((!q.isEmpty()) && num[q.peekLast()] <= num[i])
                q.pollLast();
            q.add(i);
            if(begin >= 0)
                res.add(num[q.peekFirst()]);
        }
        return res;
    }


    public ArrayList<Integer> maxInWindows2(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> deque = new LinkedList<Integer>();
        if(size==0||num==null){
            return res;
        }
        //手动将第一个滑动窗口的值输入到队列里面
        for(int i =0;i<size-1;i++){
            while(!deque.isEmpty()&&num[i]>num[deque.peekLast()]){
                deque.pollLast();
            }
            deque.addLast(i);
        }

        //开始讲滑动窗口在整个数组中滑动
        for(int i =size-1;i<num.length;i++){
            while(!deque.isEmpty()&&num[i]>num[deque.peekLast()]){
                deque.pollLast();
            }
            deque.addLast(i);
            if(i>=deque.peekFirst()+size){
                deque.pollFirst();
            }
            if(!deque.isEmpty()){
                res.add(num[deque.peekFirst()]);
            }
        }
        return res;
    }



    public static void main(String[] args) {
        int[] num ={2,3,4,2,6,2,5,1};
        int k = 3;
        Demo64 d = new Demo64();
        d.maxInWindows2(num,k);
    }

}
