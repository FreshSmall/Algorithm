package com.frame_work;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo{

    private static Map<String,String> conMap = new ConcurrentHashMap<>();

    public static void testPut(){
        conMap.put("1","ceshi");
    }


    public static void main(String[] args) {
        int a = new Integer(1).hashCode();
    }
}
