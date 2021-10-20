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
import java.util.Iterator;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/19 20:39
 **/
public class MultiServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        Selector selector = Selector.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(8083));
        server.register(selector, SelectionKey.OP_ACCEPT);

        int coreNum = Runtime.getRuntime().availableProcessors();
        Processor[] processors = new Processor[2 * coreNum];
        for (int i = 0; i < processors.length; i++) {
            processors[i] = new Processor(selector);
        }
        int index = 0;
        while (true) {
            if (selector.select() == 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);
                    Processor processor = processors[(int) ((index++) % coreNum)];
                    processor.addChannel(socketChannel);
                    processor.wakeup();
                }
            }
        }
    }

    static class Processor {

        private Selector selector;

        public Processor(Selector selector) throws IOException {
            this.selector = selector;
            start();
        }

        public void addChannel(SocketChannel socketChannel) throws ClosedChannelException {
            socketChannel.register(selector,SelectionKey.OP_READ);
        }

        public void wakeup() {
            selector.wakeup();
        }

        public void start() throws IOException {
            while (true) {
                if (selector.select(100) == 0) {
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    iterator.remove();
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        int count = socketChannel.read(buffer);
                        if (count < 0) {
                            socketChannel.close();
                            key.cancel();
                            System.out.println(socketChannel + "Read ended");
                            continue;
                        } else if (count == 0) {
                            System.out.println(socketChannel + "Message size is 0");
                            continue;
                        } else {
                            System.out.println(
                                socketChannel + ",Read message" + new String(buffer.array()));
                        }
                    }
                }
            }
        }
    }
}
