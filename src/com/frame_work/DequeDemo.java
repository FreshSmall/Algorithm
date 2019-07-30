package com.frame_work;

import java.util.ArrayDeque;

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

    public static void main(String[] args) {
        DequeDemo dequeDemo = new DequeDemo();
        dequeDemo.testArrayDeque();
    }
}
