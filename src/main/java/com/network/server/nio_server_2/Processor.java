/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/18 17:40
 **/
@Slf4j
public class Processor implements Runnable {

    private Selector selector;
    private static final Queue<SocketChannel> newConnections = new ConcurrentLinkedQueue<>();
    private RequestChannel requestChannel;

    public Processor(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {

        while (true) {
            // 配置新的连接地址
            try {
                // register any new responses for writing
                configureNewConnections();
                // processNewResponses();
                // 发送缓存的响应对象给客户端
                // poll();
                // 处理已经接收响应的连接
                processCompletedReceives();
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void accept(SocketChannel socketChannel, Selector selector) {
        newConnections.add(socketChannel);
        this.selector = selector;
        // selector.wakeup();
    }

    private void configureNewConnections() throws ClosedChannelException {
        while (!newConnections.isEmpty()) {
            SocketChannel channel = newConnections.poll();
            channel.register(selector, SelectionKey.OP_READ);
        }
    }

    private void processNewResponses() {
        String curr = requestChannel.receiveResponse("id");
        while (curr != null) {
            try {
                System.out.println("返回响应：" + curr);
            } finally {
                curr = requestChannel.receiveResponse("");
            }
        }
    }

    private void poll() throws IOException {
        int ready = selector.select(300);
    }


    private void processCompletedReceives() throws IOException {
        try {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                // 连接完成
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    buffer.clear();
                    int len = clientChannel.read(buffer);

                    if (len != 1) {
                        System.out.println(new String(buffer.array(), 0, len));
                    }
                    ByteBuffer bufferToWrite = ByteBuffer.wrap("I am Server".getBytes());
                    clientChannel.write(bufferToWrite);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
