/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/6/23 9:41 下午
 **/
public class ThroughputDemo {

    private static volatile List<Byte[]> PIG = new ArrayList<>();
    private static final int PIG_COUNT = 50;
    private static volatile int COUNT = 0;

    static class PigEat implements Runnable {

        @Override
        public void run() {
            while (true) {
                PIG.add(new Byte[30 * 1024 * 1024]);
                if (COUNT > PIG_COUNT) {
                    return;
                }
                sleep(100);
            }
        }
    }

    static class PigDigestion implements Runnable {

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            while (true) {
                sleep(2000);
                COUNT += PIG.size();
                PIG = new ArrayList<>();
                if (COUNT > PIG_COUNT) {
                    System.out.format("Digested %d pigs in %d ms.%n", COUNT,
                        System.currentTimeMillis() - start);
                    return;
                }
            }
        }
    }


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new PigEat()).start();
        new Thread(new PigDigestion()).start();
    }
}
