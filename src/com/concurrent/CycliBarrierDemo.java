package com.concurrent;

import java.sql.Time;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * J.U.C之并发工具类：CycliBarrier
 * 作用：让一组现场到达一个屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被拦截下来的线程才会继续干活
 */
public class CycliBarrierDemo {

    private static CyclicBarrier cyclicBarrier;

    static class CycliBarrierThread implements Runnable{
        @Override
        public void run() {
            System.out.println("线程："+Thread.currentThread().getName()+"到达了");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("线程："+Thread.currentThread().getName()+"继续开始执行");
        }
    }

    static class InterruptThread implements Runnable{
        @Override
        public void run() {
            System.out.println("中断线程："+Thread.currentThread().getName()+"到达了");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("中断线程："+Thread.currentThread().getName()+"继续开始执行");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程开始干活了");
            }
        });

        for (int i = 0; i < 3; i++) {
            new Thread(new CycliBarrierThread()).start();
        }

        Thread t1 = new Thread(new InterruptThread());
        t1.start();

        TimeUnit.SECONDS.sleep(3);

        t1.interrupt();




    }
}
