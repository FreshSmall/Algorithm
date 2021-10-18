/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/14 10:45
 **/
public class Acceptor {

    private Selector selector;
    private Processor[] processors = new Processor[5];

    public Acceptor() throws IOException {
        for (int i = 0; i < 5; i++) {
            processors[i] = new Processor();
            new Thread(processors[i]).start();
        }
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8083));
        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void startUp() throws IOException {
        int currentProcessor = 0;
        while (true) {
            if (selector.select(100) == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                // 连接完成
                if (key.isAcceptable()) {
                    accept(key, processors[currentProcessor]);
                } else {
                    throw new RuntimeException("server Socker 连接异常");
                }
                // 轮询 processor 处理相关连接请求
                currentProcessor = (currentProcessor + 1) % 5;
            }
        }
    }


    private void accept(SelectionKey key, Processor processor) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
            .channel();
        // 创建sockerchannel 对象
        SocketChannel sockerChannel = serverSocketChannel.accept();
        sockerChannel.configureBlocking(false);
        sockerChannel.socket().setTcpNoDelay(true);
        sockerChannel.socket().setKeepAlive(true);
        processor.accept(sockerChannel,selector);
    }
}
