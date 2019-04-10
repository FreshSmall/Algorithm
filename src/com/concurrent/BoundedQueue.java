package com.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueue<T> {
    private Object[] items;
    //添加的下标，删除的下标和数组的当前数量
    private int addIndex,removeIndex,count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size){
        items = new Object[size];
    }

    //添加一个元素，如果数组满，则添加线程进入等待状态，直到有空位
    public void add(T t) throws InterruptedException {
        lock.lock();
        try{
            while (items.length == count) {
                notFull.await();//数组已满，增加元素的线程需要停止
            }
            items[addIndex] = t;
            if(++addIndex==items.length){
                addIndex=0;
            }
            count++;
            notEmpty.signal();//数组中增加了元素，可以删除数组中的元素了
        }finally {
            lock.unlock();
        }
    }

    //由头部删除一个元素，如果数组为空，则删除线程进入等待状态，知道有新添加元素
    public T remove() throws InterruptedException {
        lock.lock();
        try{
            while (count == 0) {
                notEmpty.await();//数组中元素为null，删除元素的线程停止
            }
            Object x = items[removeIndex];
            if(++removeIndex==items.length){
                removeIndex=0;
            }
            --count;
            notFull.signal();//线程中元素可以增加元素
            return (T) x;
        }finally {
            lock.unlock();
        }
    }
}
