/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_3;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/19 19:13
 **/
public class SingleServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey bossKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        bossKey.interestOps(SelectionKey.OP_ACCEPT);
        serverSocketChannel.bind(new InetSocketAddress(8083));
        while (true) {
            if (selector.select(100) == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    System.out.println("连接开始");
                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    // 2. 关联selector
                    SelectionKey readKey = sc.register(selector, SelectionKey.OP_READ);
                    new Thread(new Worker(selector)).start();
                    // readKey.attach(new Processor());
                } /*else if (key.isReadable()) {
                    Processor processor = (Processor) key.attachment();
                    processor.process(key);
                }*/
            }
        }
    }

    static class Worker implements Runnable {

        private Selector selector;

        public Worker(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (selector.select(100) == 0) {
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        String request = "";
                        while (true) {
                            try {
                                if (!(clientChannel.read(readBuffer) > 0)) {
                                    break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            // 切换为buffer 读模式
                            readBuffer.flip();
                            // 读取buffer中的内容
                            request += Charset.forName("UTF-8").decode(readBuffer);
                            System.out.println("返回响应：" + request);
                        }
                        try {
                            clientChannel.register(selector, SelectionKey.OP_READ);
                        } catch (ClosedChannelException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }
    }

    static class Processor {

        public void process(SelectionKey selectionKey) throws IOException {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            int count = socketChannel.read(buffer);
            if (count < 0) {
                socketChannel.close();
                selectionKey.cancel();
                System.out.println(socketChannel + " Read ended");
            }
            System.out.println(socketChannel+"Read message "+new String(buffer.array()));
        }
    }
}
