package com.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Future<Integer> calculate(Integer number) {
        return executorService.submit(() -> {
            Thread.sleep(10);
            return number * number;
        });
    }

    public static void main(String[] args) {
        FutureDemo demo = new FutureDemo();
        Future<Integer> future = demo.calculate(100);
        while (!future.isDone()) {
            System.out.println("任务没有执行完毕");
        }
        Integer x = null;
        try {
            x = future.get();
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(x);
    }
}
