package com.threadpool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * @author: yinchao
 * @ClassName: CompletableFutureTest
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/7/1 23:07
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 30; i++) {
            CompletableFuture.runAsync(() -> {
                try {
                    // 模拟耗时操作
                    Thread.sleep(20000);
                    System.out.println("异步任务执行完成,执行线程：" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("主线程执行完成");
    }
}
