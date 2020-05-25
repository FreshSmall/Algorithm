package com.concurrent;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentransWriteReadLockDemo {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static class IncrementVauleThread implements Runnable {

        @Override
        public void run() {

            lock.writeLock().lock();
            lock.readLock().lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("线程：" + Thread.currentThread().getName() + "---" + i);
            }
            lock.readLock().unlock();
            lock.writeLock().unlock();

        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new IncrementVauleThread()).start();
        }

    }
}
