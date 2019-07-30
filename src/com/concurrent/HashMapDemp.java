package com.concurrent;

import java.util.HashMap;
import java.util.UUID;

public class HashMapDemp {

    private static final HashMap<String,String> map = new HashMap<String,String>(4);
    public static void main(String[] args) throws InterruptedException {
        /*Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf"+i).start();
                }
            }
        },"ftf");
        t.start();
        t.join();*/
        map.put("1","1");
        map.put("5","1");
        map.put("9","1");
        map.put("13","1");
        map.put("17","1");
        System.out.println(map.size());
    }
}
