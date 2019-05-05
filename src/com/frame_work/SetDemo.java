package com.frame_work;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class SetDemo {

    public void testHashSet(){
        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.add(1);
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

    public static void main(String[] args) {
        SetDemo setDemo = new SetDemo();
        setDemo.testHashSet();
    }
}
