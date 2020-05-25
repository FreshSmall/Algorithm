package com.concurrent;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞队列的源码实现
 */
public class BlockQueueDemo<E> {

    private Object[] item;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private int count;
    private int index;

    /**
     * 有界的默认集合的阻塞队列
     */
    public void testArrayBlockingQueue() {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(10);
    }


    public BlockQueueDemo(int size) {
        this.item = new Object[size];
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
        count = size;
        index = 0;
    }

    public void add(E e) {
        Object[] item = this.item;
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            if (index == item.length) {
                notFull.await();
            } else {
                item[index++] = e;
                notEmpty.signal();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void remove(int indexNum) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        if (indexNum < 0 || indexNum > index) {
            throw new IllegalArgumentException("删除集合的指标不存在，请重新检查");
        }
        try {
            if (index == 0) {
                notEmpty.await();
            }else{
                for (int i = indexNum; i < index-1; i++) {
                    item[i] = item[i + 1];
                }
                index--;
                notFull.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Consumer implements Runnable{

        private BlockQueueDemo<Integer> blockQueueDemo;
        public Consumer(BlockQueueDemo<Integer> blockQueueDemo){
            this.blockQueueDemo = blockQueueDemo;
        }
        @Override
        public void run() {
            while(true){
                blockQueueDemo.remove(0);
                System.out.println("线程"+Thread.currentThread().getName()+"消费任务成功");
            }
        }
    }

    static class Producer implements Runnable{

        private BlockQueueDemo<Integer> blockQueueDemo;
        public Producer(BlockQueueDemo<Integer> blockQueueDemo){
            this.blockQueueDemo = blockQueueDemo;
        }
        @Override
        public void run() {
            while(true){
                for(int i=1;i<=5;i++){
                    blockQueueDemo.add(i);
                    System.out.println("线程"+Thread.currentThread().getName()+"添加任务成功,添加的数值大小为："+i);
                }
            }
        }
    }


    public static void main(String[] args) {
        BlockQueueDemo<Integer> demo  =new BlockQueueDemo<Integer>(3);
        Consumer consumer = new Consumer(demo);
        Producer producer = new Producer(demo);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
