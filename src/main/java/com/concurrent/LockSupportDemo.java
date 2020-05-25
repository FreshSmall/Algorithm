package com.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport类似于二元信号量（只有一个许可证可供使用），如果这个许可证还没有被占用，当前线程可以获取许可继续执行，如果许可已经
 * 被占用，当前线程阻塞，等待获取许可
 */
public class LockSupportDemo {

    static class Thread1 extends Thread{

        @Override
        public void run() {
            LockSupport.park(this);
            System.out.println("线程thread1开始运行");
        }
    }

    static class Thread2 extends Thread{

        @Override
        public void run() {
            LockSupport.park(this);
            System.out.println("线程thread2开始运行");
        }
    }



    public static void main(String[] args) throws InterruptedException {

        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();


        LockSupport.unpark(t2);

        Thread.sleep(1000);

        System.out.println("ceshi123");

        LockSupport.unpark(t1);
    }
}
