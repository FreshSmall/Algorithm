/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_6;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/16 14:44
 **/
public class Handler implements Runnable, Closeable {

    private final BlockingQueue<Call> callQueue;

    private volatile boolean running = true;


    public Handler(BlockingQueue<Call> callQueue) {
        System.out.println("start Handler..");
        this.callQueue = callQueue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Call call = callQueue.poll(100, TimeUnit.MILLISECONDS);
                if (call == null) {
                    continue;
                }
                // Handler处理Call，对传过来的数据做Base64加密，其实在这里实现不同的方法
                // 一个简单的rpc调用就可以实现了
                byte[] encode = call.dataBytes;
                System.out.println("response a msg,content = " + new String(encode));
                ByteBuffer bufferToWrite = ByteBuffer.wrap("I am Server".getBytes());
                call.channel.write(bufferToWrite);
            } catch (InterruptedException | IOException ignored) {
                ignored.printStackTrace();
            }
        }
    }

    @Override
    public void close() {
        running = false;
    }

}
