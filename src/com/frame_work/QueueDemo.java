package com.frame_work;

import java.util.Iterator;
import java.util.LinkedList;

public class QueueDemo {

    /**
     * 属于队列范畴
     */
    public void testLinkList(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        Iterator<Integer> iterator =  list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {
        QueueDemo queueDemo = new QueueDemo();
        queueDemo.testLinkList();
    }
}
