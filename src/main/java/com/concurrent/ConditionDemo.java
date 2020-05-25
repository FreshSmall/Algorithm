package com.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发锁的条件用法
 */
public class ConditionDemo {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();




    public static void main(String[] args) throws InterruptedException {

        condition.await();

        condition.signal();
    }
}
