package com.frame_work;

import java.util.*;

public class MapDemo {

    public  void testMapDemo(){
        Map<String,String> map = new HashMap<String,String>(4);
        map.put("1","2");
        map.put("1","3");
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.print(entry.getKey()+"==");
            System.out.println(entry.getValue());
        }
    }

    public void testLinkedHashMap(){
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("1","2");
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
        mapDemo.testMapDemo();
        System.out.println(hash("1"));
        int h = "5".hashCode();
        System.out.println((h^h>>>16)&3);
    }


}
