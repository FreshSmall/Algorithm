package com.frame_work;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListDemo {

    public void testArrayList(){
        List<Integer> list = new ArrayList();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }



    public static void main(String[] args) {
        ListDemo listDemo = new ListDemo();
    }
}
