package com.network.client;

import com.network.server.business_process.ClientRequestThread;

import java.util.concurrent.CountDownLatch;

public class BioSocketClient {

    public static void main(String[] args) throws InterruptedException {
        Integer clientNumber = 20;
        CountDownLatch countDownLatch = new CountDownLatch(clientNumber);
        //分别开始启动这20个客户端，并发访问
        for (int index = 0; index < clientNumber; index++, countDownLatch.countDown()) {
            ClientRequestThread client = new ClientRequestThread(countDownLatch, index);
            new Thread(client).start();
        }
    }
}
