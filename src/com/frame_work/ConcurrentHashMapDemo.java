package com.frame_work;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    public void testConcurrentHashMapDemo() {
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.print(entry.getKey()+"===");
            System.out.println(entry.getValue());
        }
    }


    public static void main(String[] args) {
        ConcurrentHashMapDemo hashMapDemo = new ConcurrentHashMapDemo();
        hashMapDemo.testConcurrentHashMapDemo();
    }
}
