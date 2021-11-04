/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/14 10:45
 **/
@Slf4j
public class Acceptor {

    private static ExecutorService pool = Executors.newFixedThreadPool(100);
    private Processor processor;

    public void startUp() throws IOException {
        int currentProcessor = 0;
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8083));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        processor = new Processor(selector);
        new Thread(processor).start();
        while (true) {
            try {
                if (selector.select(100) > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = it.next();
                        it.remove();
                        // 连接完成
                        if (key.isAcceptable()) {
                            System.out.println(key);
                            accept(key, processor, selector);
                        } else {
                            throw new RuntimeException("server Socker 连接异常");
                        }
                        // 轮询 processor 处理相关连接请求
                        currentProcessor = (currentProcessor + 1) % 5;
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                e.printStackTrace();
            }
        }
    }


    private void accept(SelectionKey key, Processor processor, Selector selector)
        throws IOException {
        System.out.println("客户端有连接接入");
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
            .channel();
        // 创建sockerchannel 对象
        SocketChannel sockerChannel = serverSocketChannel.accept();
        sockerChannel.configureBlocking(false);
        sockerChannel.socket().setTcpNoDelay(true);
        sockerChannel.socket().setKeepAlive(true);
        processor.accept(sockerChannel, selector);
    }
}
