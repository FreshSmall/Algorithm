package com.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompleteFutureDemo {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public int calaulate(Integer number) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number * number;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int number = 100;
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            CompleteFutureDemo demo = new CompleteFutureDemo();
            return demo.calaulate(number);
        }, executorService);
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println("v" + v);
            System.out.println("e:" + e);
        });
        System.out.println(f.get());
        executorService.shutdown();
    }
}
