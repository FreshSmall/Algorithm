/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_5;

import com.sun.org.apache.bcel.internal.generic.Select;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/11 19:39
 **/
@Slf4j
public class Processor implements Runnable {

    private Selector selector;
    private static final Queue<NioSocketChannel> queue = new ConcurrentLinkedQueue<>();

    public Processor(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                NioSocketChannel socketChannel = queue.poll();
                handleConnect(socketChannel);
            }
        }
    }

    private void handleConnect(NioSocketChannel socketChannel) {
        SocketChannel sc = socketChannel.getSocketChannel();
        try {
            int readyOps = socketChannel.getReadyOps();
            if ((readyOps & (1 << 4)) != 0) {
                try {
                    sc.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    log.info(e.getMessage(), e);
                }
            } else if ((readyOps & (1 << 0)) != 0) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.clear();
                int len = sc.read(buffer);
                if (len != -1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }
                ByteBuffer bufferToWrite = ByteBuffer.wrap("I am Server".getBytes());
                sc.write(bufferToWrite);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void process(NioSocketChannel socketChannel) {
        queue.add(socketChannel);
        selector.wakeup();
    }
}
