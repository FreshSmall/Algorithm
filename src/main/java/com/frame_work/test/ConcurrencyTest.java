package com.frame_work.test;

import lombok.SneakyThrows;

import java.util.Set;

public class ConcurrencyTest {


    public static void main(String[] args) throws InterruptedException {
        new Thread(new Thread1()).start();
        Thread.sleep(5000);
        new Thread(new Thread2()).start();
        new Thread(new Thread3()).start();

        Thread.sleep(2000);

    }
}

class Thread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            ConcurrencyPool.addWsInfo("test", "abcd" + i);
        }
    }
}

class Thread2 implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Set<String> test = ConcurrencyPool.getWsInfo("test");
            for (String s : test) {
                System.out.println(test);
            }
        }

    }
}

class Thread3 implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            ConcurrencyPool.removeWsInfo("test", "abcd" + i);
        }
    }
}
