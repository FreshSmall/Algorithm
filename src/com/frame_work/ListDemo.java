package com.frame_work;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.ReentrantLock;

public class ListDemo {

    public void testArrayList(){
        List<Integer> list = new ArrayList();
        list.add(1);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public void testCopyOnWriteArrayList(){
        CopyOnWriteArrayList<Integer> cwal = new CopyOnWriteArrayList<>();
        cwal.add(1);
        cwal.add(2);
        cwal.add(3);
        cwal.addIfAbsent(2);
        Iterator<Integer> iterator = cwal.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    public void testToArray(){
        List<String> str1 = new ArrayList<String>();
        str1.add("ab");
        str1.add("bc");
        Object[] strs1 = str1.toArray();
        System.out.println("strs1:");
        System.out.println(str1.toString());
        System.out.println(strs1.getClass());
        System.out.println(str1.getClass());

        List<String> str2 = Arrays.asList(new String[]{"ab", "bc"});
        String[] strs2 = (String[]) str2.toArray();
        System.out.println("strs2:");
        System.out.println(str2.toString());
        System.out.println(strs2.getClass());
        System.out.println(str2.getClass());
    }

    public void copyOnWrite(){
        MyList myList = new MyList(10);

        Runnable read = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println("读线程读取的值"+myList.get(i));
                }
            }
        };

        Runnable write = new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<10;i++){
                    System.out.println("写线程开始插入值");
                    myList.set(i);
                }
            }
        };

        new Thread(read).start();
        new Thread(write).start();

    }

    public void testToArray1(){
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
        listDemo.testCopyOnWriteArrayList();
    }



    class MyList{
        int size = 0;
        final transient ReentrantLock lock = new ReentrantLock();

        private transient volatile Object[] array;

        public MyList(int size){
            array = new Object[size];
        }

        public Object get(int index){
            return array[index];
        }

        public void set(Object object){
            lock.lock();
            try{
                array[size++]=object;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

}
