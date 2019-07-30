package com.frame_work;

import java.util.ArrayList;
import java.util.Iterator;
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


    public void testToArray(){
        List<String> list = new myList();
        list.add("1");
        list.add("2");
        Object[] objects = list.toArray();
        System.out.println(objects.length);
    }

    public void testSystemCopy(){
        int[] a = new int[]{1,2,3,4,5};
        int[] b = new int[a.length];
        System.arraycopy(a,0,b,0,a.length);
        System.out.println(b.length);
    }

    class myList extends ArrayList<String>{

        public Object[] toArray(){
            return new Object[]{"1","2","3"};
        }
    }



    public static void main(String[] args) {
        ListDemo listDemo = new ListDemo();
        listDemo.testSystemCopy();
    }
}
