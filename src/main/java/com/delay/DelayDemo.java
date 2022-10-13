package com.delay;

import com.google.common.primitives.Ints;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayDemo {


    @Data
    static class DelayTask implements Delayed {
        private String name;
        private long time;

        public DelayTask(String name, long delayTime) {
            this.name = name;
            this.time = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(@NotNull TimeUnit unit) {
            long diff = this.time - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(@NotNull Delayed o) {
            return Ints.saturatedCast(this.time - ((DelayTask) o).getTime());
        }
    }

    static class Producer implements Runnable {

        private DelayQueue<DelayTask> queue;

        public Producer(DelayQueue<DelayTask> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    DelayTask task = new DelayTask("延迟任务" + (i + 1), 5000);
                    System.out.println("Put object: " +task.toString()+",put 时间="+System.currentTimeMillis());
                    queue.put(task);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private DelayQueue<DelayTask> queue;

        public Consumer(DelayQueue<DelayTask> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    DelayTask task = queue.take();
                    System.out.println("taskInfo:" + task+"get 时间="+System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayTask> queue = new DelayQueue<>();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        service.submit(producer);
        service.submit(consumer);

        service.awaitTermination(5,TimeUnit.MINUTES);
        service.shutdown();

    }
}
