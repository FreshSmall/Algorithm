package com.frame_work;

import java.util.*;

public class MapDemo {

    public  void testHashMapDemo(){
        Map<String,String> map = new HashMap<String,String>(4);
        map.put("1","1");
        map.put("5","5");
        map.put("4","4");
        map.put("2","2");
        map.put("3","3");

        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.print(entry.getKey()+"==");
            System.out.println(entry.getValue());
        }

    }

    public void testLinkedHashMap(){
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("1","1");
        map.put("5","5");
        map.put("4","4");
        map.put("2","2");
        map.put("3","3");
        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.print(entry.getKey()+"==");
            System.out.println(entry.getValue());
        }
    }

    public void testTreeMap(){
        Map<String,String> map = new TreeMap<String,String>();
        map.put("1","1");
        map.put("5","5");
        map.put("4","4");
        map.put("2","2");
        map.put("3","3");
        for(Map.Entry<String,String> entry:map.entrySet()){
            System.out.print(entry.getKey()+"==");
            System.out.println(entry.getValue());
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        MapDemo mapDemo = new MapDemo();
//        mapDemo.testLinkedHashMap();
//        mapDemo.testHashMapDemo();
        mapDemo.testTreeMap();
    }


}
