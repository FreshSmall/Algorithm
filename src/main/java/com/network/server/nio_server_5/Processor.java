/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_5;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
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
    private static final Queue<SocketChannel> queue = new ConcurrentLinkedQueue<>();

    public Processor() throws IOException {
        this.selector = Selector.open();
    }

    @Override
    public void run() {
        while (true) {
            configureNewConnections();
            poll();
        }
    }

    private void configureNewConnections() {
        if (!queue.isEmpty()) {
            SocketChannel socketChannel = queue.poll();
            handleConnect(socketChannel);
        }
    }

    private void poll() {
        try {
            int readyKeys = selector.select(100);
            if (readyKeys > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if (key.isReadable()) { // 读写事件
                        SocketChannel sc = null;
                        try {
                            sc = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            buffer.clear();
                            int len = sc.read(buffer);
                            if (len != -1) {
                                System.out.println(new String(buffer.array(), 0, len));
                            }
                            ByteBuffer bufferToWrite = ByteBuffer.wrap("I am Server".getBytes());
                            sc.write(bufferToWrite);
                        } catch (IOException e) {
                            log.info(e.getMessage(), e);
                        } finally {
                            if (sc != null) {
                                try {
                                    sc.close();
                                } catch (IOException e) {
                                    log.info(e.getMessage(), e);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleConnect(SocketChannel socketChannel) {
        try {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public void process(SocketChannel socketChannel) {
        queue.add(socketChannel);
        selector.wakeup();
    }
}
