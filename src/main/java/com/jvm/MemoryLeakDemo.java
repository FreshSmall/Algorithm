package com.jvm;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author: yinchao
 * @ClassName: MemoryLeakDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/9/14 00:02
 */
public class MemoryLeakDemo {

    private static Map<Integer, byte[]> cache = new WeakHashMap<>();

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            // 缓存不断增长，没有清理逻辑
            cache.put(i++, new byte[1024 * 512]); // 512KB
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
