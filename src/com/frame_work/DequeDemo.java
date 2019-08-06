package com.frame_work;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DequeDemo {


    public void testArrayDeque(){
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(4);
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(4);

        arrayDeque.addLast(5);
        arrayDeque.addLast(6);
        arrayDeque.addLast(7);
        arrayDeque.addLast(8);

    }

    public void testLinkedBlockingDeque(){
        LinkedBlockingDeque<Integer> lbq = new LinkedBlockingDeque<Integer>();
        lbq.add(1);
        lbq.add(2);
        lbq.add(3);
        Iterator<Integer> iterator = lbq.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void testConcurrentLinkedDeque(){
        ConcurrentLinkedDeque<Integer> cld = new ConcurrentLinkedDeque<Integer>();
        cld.add(1);
        cld.add(2);
        cld.add(3);
        Iterator<Integer> iterator  = cld.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        DequeDemo dequeDemo = new DequeDemo();
        dequeDemo.testArrayDeque();
    }
}
