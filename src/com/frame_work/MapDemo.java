package com.frame_work;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapDemo {

    public void testHashMapDemo() {
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("1", "1");
        map.put("5", "5");
        map.put("4", "4");
        map.put("2", "2");
        map.put("3", "3");

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "==");
            System.out.println(entry.getValue());
        }

    }

    public void testLinkedHashMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "1");
        map.put("5", "5");
        map.put("4", "4");
        map.put("2", "2");
        map.put("3", "3");
        map.get("1");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "==");
            System.out.println(entry.getValue());
        }
    }

    public void testTreeMap() {
        Map<String, String> map = new TreeMap<String, String>();
        map.put("1", "1");
        map.put("5", "5");
        map.put("4", "4");
        map.put("2", "2");
        map.put("3", "3");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "==");
            System.out.println(entry.getValue());
        }
    }

    public void testWeakHashMap() {
        WeakHashMap<String, String> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("1", "1");
        weakHashMap.put("2", "2");
        System.out.println(weakHashMap.put("2", "3"));
        System.out.println(weakHashMap.put("2", "4"));
        System.out.println(weakHashMap.put("3", "4"));
        System.out.println(weakHashMap.toString());
    }


    public void testConcurrentSkipListMap() {
        ConcurrentSkipListMap<Integer, String> cslm = new ConcurrentSkipListMap<Integer, String>();
        cslm.put(1, "1");
        cslm.put(6, "6");
        cslm.put(5, "5");
        cslm.put(3, "3");
        cslm.put(17, "17");
        cslm.put(16, "17");
        cslm.put(15, "17");
        cslm.put(14, "17");
        cslm.put(13, "17");
        cslm.put(18, "17");
        cslm.put(20, "17");
        for (Map.Entry<Integer, String> entry : cslm.entrySet()) {
            System.out.print(entry.getKey() + "==");
            System.out.println(entry.getValue());
        }
    }


    public void testConcurrentHashMapDemo() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "===");
            System.out.println(entry.getValue());
        }
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        MapDemo mapDemo = new MapDemo();
        mapDemo.testConcurrentSkipListMap();

        mapDemo.testConcurrentSkipListMap();
    }

    class LRU<K, V> extends LinkedHashMap<K, V> {
        private int capacity;

        public LRU(int size) {
            super(size, 0.75f, true);
            this.capacity = size;
        }


        @Override
        public boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }

    }


}
