/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_6;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/16 14:43
 **/
public class Reader implements Runnable, Closeable {

    private final BlockingQueue<Call> callQueue;

    private Selector selector;
    private volatile boolean running = true;


    public Reader(BlockingQueue<Call> callQueue) {
        System.out.println("start Reader..");
        this.callQueue = callQueue;
    }

    @Override
    public void run() {

        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            System.out.println("Failed to open select in Reader");
            throw new RuntimeException("Failed to open select in Reader");
        }
        while (running) {
            try {
                if (selector.select(100) > 0) {
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();
                        if (key.isReadable()) {
                            doRead(key);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (selector != null) {
                selector.close();
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public void addChannel(SocketChannel channel) {
        try {
            System.out.println("add reading channels..");
            // 因为有可能Select会在轮询中block，所以wakeUp是有必要的
            selector.wakeup();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    private void doRead(SelectionKey key) {
        /*SocketChannel channel = (SocketChannel) key.channel();
        byte[] dataBytes;
        try {
            // 从Channel中读取数据，并将Call推到阻塞队列中
            ByteBuffer dataLengthBuf = ByteBuffer.allocate(4);
            channel.read(dataLengthBuf);
            dataLengthBuf.flip();
            int dataLength = dataLengthBuf.getInt();
            ByteBuffer dataBuf = ByteBuffer.allocate(dataLength);
            channel.read(dataBuf);
            dataBuf.flip();
            dataBytes = dataBuf.array();
        } catch (IOException ignored) {
            ignored.printStackTrace();
            return;
        }
        callQueue.offer(new Call(channel, dataBytes));*/
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
            e.printStackTrace();
        } finally {
            if (sc != null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void close() {
        running = false;
    }

}
