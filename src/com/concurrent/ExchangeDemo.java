package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * J.U.C并发工具类之Exchange类
 */
public class ExchangeDemo {

    static class Producer implements Runnable {

        //消费者消费和生产者生产的数据结构
        private List<String> buffer;
        //生产者和消费者的交换对象
        private Exchanger<List<String>> exchanger;

        public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++){
                System.out.println("生产者开始提供产品，第"+i+"次提供产品，buffer容量大小："+buffer.size());
                for(int j = 0;j<3;j++){
                    buffer.add("buffer,i:"+i+"---j:"+j);
                }
                try {
                    exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{

        //消费者消费和生产者生产的数据结构
        private List<String> buffer;
        //生产者和消费者的交换对象
        private Exchanger<List<String>> exchanger;

        public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
            this.buffer = buffer;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            for(int i=0;i<5;i++){
                try {
//                    buffer = exchanger.exchange(buffer);
                    System.out.println("消费者开始第"+i+"次消费产品");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for(int j=0;j<3;j++){
//                    System.out.println(buffer.remove(0));
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<String>();
        List<String> buffer2 = new ArrayList<String>();
        Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
        Producer producer = new Producer(buffer1,exchanger);
        Consumer consumer = new Consumer(buffer2,exchanger);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
