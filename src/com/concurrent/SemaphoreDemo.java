package com.concurrent;

import java.util.concurrent.Semaphore;

/**
 * J.U.C并发包之信号量
 */
public class SemaphoreDemo {

    static class Parking {
        private Semaphore semaphore;

        Parking(int count) {
            semaphore = new Semaphore(count);
        }

        public void park() {
            try {
                semaphore.acquire();
                long time = 3000;
                System.out.println("车" + Thread.currentThread().getName() + "开进停车场，停放时间:" + time + "ms");
                Thread.sleep(time);
                System.out.println("车" + Thread.currentThread().getName() + "开出停车场");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

    }

    static class Emplyee extends Thread {
        private Parking parking;

        Emplyee(Parking parking) {
            this.parking = parking;
        }

        public void run() {
            parking.park();
        }
    }

    public static void main(String[] args) {
        Parking parking = new Parking(5);
        for(int i =0;i<6;i++){
            new Emplyee(parking).start();
        }
    }


}
