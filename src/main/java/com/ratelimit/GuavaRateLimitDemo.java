package com.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimitDemo {

    private RateLimiter rateLimiter = RateLimiter.create(5);



    public void testDeal() {

        while (!this.rateLimiter.tryAcquire(1)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.printf("线程%s执行被限流\n", Thread.currentThread().getName());
        }
            try {
                Thread.sleep(100);
                System.out.printf("线程%s执行成功\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        GuavaRateLimitDemo demo = new GuavaRateLimitDemo();
        //模拟瞬间生产100个线程请求
        for (int i = 0; i < 20; i++) {
            new Thread(demo::testDeal).start();
        }
    }
}
