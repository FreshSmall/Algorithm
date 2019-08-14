package com.algorithm.sword_offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 */
public class Demo63 {


    public int count =0;

    PriorityQueue<Integer> minPriority = new PriorityQueue<Integer>();

    PriorityQueue<Integer> maxPriority = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void Insert(Integer num) {
        count++;
        if((count&1)==0){
            if(!maxPriority.isEmpty()&&num<maxPriority.peek()){
                maxPriority.add(num);
                num = maxPriority.poll();
            }
            minPriority.add(num);
        }else{
            if(!minPriority.isEmpty()&&num>minPriority.peek()){
                minPriority.add(num);
                num = minPriority.poll();
            }
            maxPriority.add(num);
        }
    }

    public Double GetMedian() {
        double result;
        if((count&1)==0){
            result= (minPriority.peek()+maxPriority.peek())/2.0;
        }else{
            result= maxPriority.peek();
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
