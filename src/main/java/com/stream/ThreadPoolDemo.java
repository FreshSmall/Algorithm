package com.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: yinchao
 * @ClassName: ParallelDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/7/1 09:07
 */
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(20);
        final int size = 10000;
        int corePoolSize = 50; // 核心线程数
        int maximumPoolSize = 100; // 最大线程数
        long keepAliveTime = 1; // 线程空闲时间
        TimeUnit unit = TimeUnit.MINUTES; // 时间单位
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(); // 任务队列
        long begin = System.currentTimeMillis();
        // 创建自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        ThreadPoolExecutor worker = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);

        final List<String> list = Lists.newArrayList("a", "b", "c", "d", "e");
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            executor.submit(() -> {
                long start = System.currentTimeMillis();
                list.forEach(e -> {
                    worker.submit(() -> {
                        try {
                            Thread.sleep(10 * 1000);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println(Thread.currentThread().getName() + " - value: " + e + " - index:" + finalI);
                    });
                });
                countDownLatch.countDown();
                System.out.println("index:" + finalI + "--cost: " + (System.currentTimeMillis() - start) + "ms");
            });
        }
        countDownLatch.await();
        System.out.println("任务完成。cost: " + (System.currentTimeMillis() - begin) + "ms");
    }
}
