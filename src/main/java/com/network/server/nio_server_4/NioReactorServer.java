/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_4;


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

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/1 14:29
 **/
@Slf4j
public class NioReactorServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8083));
        ssc.configureBlocking(false);

        System.out.println("server started, listening on :" + ssc.getLocalAddress());

        // Select 轮询监听channel事件（这里是注册连接事件）将其注册到new出来的`ServerSocketChannel`
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // selector.select(); 阻塞等待连接事件，当然也可以设置select方法的超时
            selector.select();
            // selector.select(); 已经和内核打交道了，可以从多路复用器中取出有效的连接
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                // 处理连接事件
                handleConnect(key);
            }
        }

    }

    private static void handleConnect(SelectionKey key) {
        if (key.isAcceptable()) { // 连接事件
            try {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                System.out.println("accept a client:" + sc.getRemoteAddress());
                // 继续注册socket的读事件
                sc.configureBlocking(false);
                sc.register(key.selector(), SelectionKey.OP_READ);
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
    }
}
