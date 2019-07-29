package com.frame_work;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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


    public static void main(String[] args) {
        ListDemo listDemo = new ListDemo();
        listDemo.copyOnWrite();
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
