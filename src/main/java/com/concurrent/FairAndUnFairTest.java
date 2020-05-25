package com.concurrent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairAndUnFairTest {

    private static ReentrantLock fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);


    public void fair() {
        testLock(fairLock);
    }

    public void unfair() {
        testLock(unfairLock);
    }

    private void testLock(Lock lock) {
        new Job(unfairLock).start();
        new Job(unfairLock).start();
        new Job(unfairLock).start();
        new Job(unfairLock).start();
        new Job(unfairLock).start();
    }


    private static class Job extends Thread {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        public void run(){
            System.out.println(Thread.currentThread().getName()+"--");
            List<Thread> arrayList = new ArrayList<Thread>(((ReentrantLock2)lock).getQUeuedThreads());
            for (int i = 0; i < arrayList.size(); i++) {
                System.out.println(arrayList.get(i).getName());
            }


            System.out.println(Thread.currentThread().getName()+"--");
            List<Thread> arrayList1 = new ArrayList<Thread>(((ReentrantLock2)lock).getQUeuedThreads());
            for (int i = 0; i < arrayList1.size(); i++) {
                System.out.println(arrayList1.get(i).getName());
            }
        }

    }


    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQUeuedThreads() {
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }

    }

    public static void main(String[] args) {
        FairAndUnFairTest fairAndUnFairTest = new FairAndUnFairTest();
        fairAndUnFairTest.unfair();
//        fairAndUnFairTest.fair();
    }
}
