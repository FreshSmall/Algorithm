package com.concurrent;


import java.util.concurrent.locks.LockSupport;

/**
 * 被中断的线程不会被暂停，依然会继续运行，线程中的终端标志位会被变化
 */
public class InterruptDemo implements Runnable {
    @Override
    public void run() {
        while(true){
            LockSupport.park();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("线程已经被中断！");
            }else{
                System.out.println("线程正在处理中");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        InterruptDemo interruptDemo = new InterruptDemo();
        Thread t= new Thread(interruptDemo);
        t.start();

        t.sleep(1000);

        t.interrupt();

    }
}
