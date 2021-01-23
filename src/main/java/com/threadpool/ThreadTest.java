/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.threadpool;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/1/23 下午12:56
 **/
public class ThreadTest extends Thread {

    public ThreadTest(Runnable runnable){
        super(runnable);
        /**
         *
         * 设置线程为守护线程，当主线程结束时，守护线程也会结束
         * 当线程为用户线程时，主线程结束，用户线程结束，jvm才能推出
         */

        setDaemon(true);
    }

    static class RunnableTest implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("测试线程开始运行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ThreadTest(new RunnableTest()).start();
    }
}
