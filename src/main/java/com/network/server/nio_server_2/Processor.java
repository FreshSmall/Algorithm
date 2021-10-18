/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/18 17:40
 **/
public class Processor implements Runnable {

    private Selector selector;
    private Queue<SocketChannel> newConnections = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {

        while (true) {
            // 配置新的连接地址
            configureNewConnections();
            // register any new responses for writing
            processNewResponses();
            // 发送缓存的响应对象给客户端
            poll();
            processCompletedReceives();
            processCompletedSends();
            processDisconnected();
        }

    }

    public void accept(SocketChannel socketChannel, Selector selector) {
        newConnections.add(socketChannel);
        this.selector = selector;
        selector.wakeup();
    }

    private void configureNewConnections() throws ClosedChannelException {
        while (!newConnections.isEmpty()) {
            SocketChannel channel = newConnections.poll();
            channel.register(selector, SelectionKey.OP_READ);
        }
    }
}
