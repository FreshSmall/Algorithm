package com.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    public void test_Future() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<String> submit = executorService.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return Thread.currentThread().getName();
            }
        });
        while (!submit.isDone()) {
            System.out.println("任务没有执行完成");
        }
        System.out.println(submit.get());
    }

    public static void main(String[] args)
        throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorThreadPoolDemo demo = new ExecutorThreadPoolDemo();
        // demo.test_newFixedThreadPool();
        demo.test_Future();
    }

}
