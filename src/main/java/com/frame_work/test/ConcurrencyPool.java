package com.frame_work.test;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class ConcurrencyPool {


    private static Map<String, Set<String>> Account_Map = new ConcurrentHashMap<>();

    public static Set<String> getWsInfo(String businessId) {
        return Account_Map.get(businessId);
    }

    public static void removeWsInfo(String businessId, String info) throws InterruptedException {
        Set<String> set = Account_Map.get(businessId);
        if (set == null) {
            return;
        }
        set.remove(info);
    }

    public static void addWsInfo(String businessId, String info) {
        Set<String> set = Account_Map.computeIfAbsent(businessId, k -> new CopyOnWriteArraySet<>());
        set.add(info);
    }
}
