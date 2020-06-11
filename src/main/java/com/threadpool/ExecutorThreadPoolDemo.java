package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/6/3 4:21 下午
 */
public class ExecutorThreadPoolDemo {

    public void test_newFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("current thread name" + Thread.currentThread().getName());
            }
        });
    }

    public static void main(String[] args) {
        ExecutorThreadPoolDemo demo = new ExecutorThreadPoolDemo();
        demo.test_newFixedThreadPool();
    }

}
