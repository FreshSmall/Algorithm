package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: yinchao
 * @ClassName: CompleteFutureSyncDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/1/12 22:13
 */
public class CompleteFutureAsyncDemo {

    public void timConsume() {
        try {
            Thread.sleep(5000);
            System.out.println("完成任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void timConsume1() {
        try {
            Thread.sleep(8000);
            System.out.println("完成任务");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompleteFutureAsyncDemo demo = new CompleteFutureAsyncDemo();
        long start = System.currentTimeMillis();
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            demo.timConsume1();
        });
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            demo.timConsume();
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            demo.timConsume();
        });
        future.get();
        future1.get();
        future2.get();
        System.out.println("cost time: " + (System.currentTimeMillis() - start));

    }
}
