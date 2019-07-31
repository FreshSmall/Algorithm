package com.frame_work;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetDemo {

    public void testHashSet(){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.add(1);
        hashSet.add(null);
        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void testTreeet(){
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        treeSet.add(1);
        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void testLinkedHashSet(){
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        lhs.add(1);
        lhs.add(3);
        lhs.add(2);
        lhs.add(4);
        Iterator<Integer> iterator = lhs.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void testCopyOnWriteArraySet(){
        CopyOnWriteArraySet<Integer> cwas = new CopyOnWriteArraySet<Integer>();
        cwas.add(1);
        cwas.add(4);
        cwas.add(4);
        cwas.add(2);
        Iterator<Integer> iterator = cwas.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public void testConcurrentSkipListSet(){
        ConcurrentSkipListSet<Integer> csls = new ConcurrentSkipListSet<>();
        csls.add(1);
        csls.add(2);
        csls.add(3);
        Iterator<Integer> iterator = csls.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        outer: for (;;) {

        }
    }

    public static void main(String[] args) {
        SetDemo setDemo = new SetDemo();
        setDemo.testConcurrentSkipListSet();
    }
}
