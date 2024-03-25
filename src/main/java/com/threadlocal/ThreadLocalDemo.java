package com.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yinchao
 * @ClassName: ThreadLocalDemo
 * @Description: ThreadLocal Demo
 * @team wuhan operational dev.
 * @date: 2022/10/24 15:20
 */
public class ThreadLocalDemo {


    private static void threadLocalTest() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("java");
        System.out.println(threadLocal.get());
        new Thread(() -> System.out.println(threadLocal.get())).start();
    }

    private static void inheritableThreadLocalTest() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("java");
        System.out.println(threadLocal.get());
        new Thread(() -> System.out.println(threadLocal.get())).start();
    }

    private static void inheritableThreadLocalPoolTest() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("java");
        System.out.println(threadLocal.get());
        // 使用线程池
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> System.out.println(threadLocal.get()));
    }

    public static void main(String[] args) {
//        threadLocalTest();
//        inheritableThreadLocalTest();
        inheritableThreadLocalPoolTest();
    }

}
