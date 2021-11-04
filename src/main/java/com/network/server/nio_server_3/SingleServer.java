/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_3;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/19 19:13
 **/
@Slf4j
public class SingleServer {

    private static ExecutorService pool = Executors.newFixedThreadPool(100);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8083));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey bossKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // bossKey.interestOps(SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                if (key.isValid() && key.isAcceptable()) {
                    System.out.println("连接开始");
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    // 2. 关联selector
                    // sc.register(selector, SelectionKey.OP_READ);
                } else if (key.isValid() && key.isReadable()) {
                    pool.submit(new Worker(key));
                }
            }
        }
    }

    static class Worker implements Callable {

        private SelectionKey key;

        public Worker(SelectionKey key) {
            this.key = key;
        }

        @Override
        public Object call() throws Exception {
            SocketChannel clientChannel = null;
            try {
                clientChannel = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.clear();
                int len = clientChannel.read(buffer);

                if (len != 1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }
                ByteBuffer bufferToWrite = ByteBuffer.wrap("I am Server".getBytes());
                clientChannel.write(bufferToWrite);
            } catch (Exception e) {
                log.info(e.getMessage(), e);
            } finally {
                if (clientChannel != null) {
                    clientChannel.close();
                }
            }
            return null;
        }
    }
}
