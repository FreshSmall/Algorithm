/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_6;


import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/16 14:42
 **/
public class Acceptor implements Runnable, Closeable {

    public final int DEFAULT_READER_SIZE = 2;
    private final int port;
    private final Reader[] readers;
    private ServerSocketChannel serverSocketChannel;
    private volatile boolean running = true;
    private Selector selector;
    private int readerIndex = 0;

    public Acceptor(int port, BlockingQueue<Call> callQueue) {
        this.port = port;

        System.out.println("start Accepter..");
        this.readers = new Reader[DEFAULT_READER_SIZE];
        for (int i = 0; i < this.readers.length; i++) {
            this.readers[i] = new Reader(callQueue);
            new Thread(this.readers[i]).start();
        }
    }

    @Override
    public void run() {
        // 绑定端口，并把serverSocketChannel的ACCEPT事件注册到Accepter的Select
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to open server socket channel");
        }

        while (running) {
            try {
                if (selector.select(100) > 0) {
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isAcceptable()) {
                            doAccept(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // close
        try {
            if (selector != null) {
                selector.close();
            }

            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    private void doAccept(SelectionKey key) throws IOException {
        // 轮训选择一个Reader
        Reader reader = this.readers[readerIndex++ % DEFAULT_READER_SIZE];
        SocketChannel socketChannel = serverSocketChannel.accept();
        InetSocketAddress remoteAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
        System.out.println(String.format("accept a connect from %s:%s",
            remoteAddress.getHostName(), remoteAddress.getPort()));
        // 将此Channel交给被选中的Reader
        reader.addChannel(socketChannel);
    }

    @Override
    public void close() {
        Arrays.stream(readers).forEach(Reader::close);
        running = false;
    }

}
