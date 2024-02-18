package com.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * callable线程测试
 *
 * @Author : yinchao
 * @create 2020/6/23 11:54 上午
 */
public class CallThreadTest {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void test_newFixedThreadPool() throws ExecutionException, InterruptedException {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("暂停20s");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("任务执行完毕");
            }
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            try {
                // 等待线程池中的任务完成，最多等待60秒
                if (!executorService.awaitTermination(20, TimeUnit.SECONDS)) {
                    // 如果在60秒内线程池仍未关闭，尝试强制关闭
                    executorService.shutdownNow();

                    // 再次等待，如果线程池仍未关闭，则进行其他处理
                    if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                        System.err.println("Thread pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                // 捕获中断异常
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }

            System.out.println("Graceful shutdown complete.");
        }));
        CompletableFuture.runAsync(() -> {
            System.out.println("暂停20s");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务执行完毕");
        }, executorService).exceptionally(e -> {
            System.out.println("任务执行异常");
            return null;
        });

    }
}
