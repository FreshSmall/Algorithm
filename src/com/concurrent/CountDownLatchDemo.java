package com.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Java并发包之CountDownLatch锁
 */
public class CountDownLatchDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(5);

    static class BossThread implements Runnable {

        @Override
        public void run() {
            System.out.println("Boss在会议室等待开会，总共有：" + countDownLatch.getCount() + "人在等待开会");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("人到齐了，开始开会吧");
        }
    }

    static class EmplyeeThread implements Runnable {

        @Override
        public void run() {
            System.out.println("线程：" + Thread.currentThread().getName() + "到达会场开始开会");
            countDownLatch.countDown();
        }
    }


    public static void main(String[] args) {
        new Thread(new BossThread()).start();


        for (long i = 0; i < 5; i++) {
            new Thread(new EmplyeeThread()).start();
        }
    }

}
