/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_5;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
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

    private static final Queue<SelectionKey> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void run() {
        while (true) {
            if (!queue.isEmpty()) {
                SelectionKey key = queue.poll();
                System.out.println("从Processor 队列中获取 key:" + key + ",queue size:" + queue.size());
                handleConnect(key);
            }
        }
    }

    private void handleConnect(SelectionKey key) {
        if (key.isAcceptable()) { // 连接事件
            try {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                System.out.println("accept a client:" + sc.getRemoteAddress());
                // 继续注册socket的读事件
                sc.configureBlocking(false);
                SelectionKey key1 = sc.register(key.selector(), SelectionKey.OP_READ);
                System.out.println("key1:" + key1);
            } catch (IOException e) {
                log.info(e.getMessage(), e);
            } finally {
            }
        } else if (key.isReadable()) { // 读写事件
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
        System.out.println("key" + key + ",连接处理完毕");
    }

    public void process(SelectionKey key) {
        if (!queue.contains(key)) {
            System.out.println("插入 key :" + key);
            queue.add(key);
        }
    }
}
