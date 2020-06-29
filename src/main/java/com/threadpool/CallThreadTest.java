package com.threadpool;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * callable线程测试
 * @Author : yinchao
 * @create 2020/6/23 11:54 上午
 */
public class CallThreadTest {

    public void test_newFixedThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("测试callable线程");
                return 1;
            }
        });
        System.out.println("执行线程返回结果："+future.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallThreadTest test = new CallThreadTest();
        test.test_newFixedThreadPool();
    }
}
