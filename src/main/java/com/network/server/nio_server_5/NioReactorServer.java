/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_5;


import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

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
        Processor processor = new Processor(selector);
        new Thread(processor).start();
        while (true) {
            // selector.select(); 阻塞等待连接事件，当然也可以设置select方法的超时
            int select = selector.select(100);
            if (select > 0) {
                // selector.select(); 已经和内核打交道了，可以从多路复用器中取出有效的连接
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    // 处理连接事件
                    ServerSocketChannel ssc1 = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc1.accept();
                    sc.configureBlocking(false);
                    processor.process(sc);
                }
            }
        }
    }
}
