/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_5;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/12 19:11
 **/
public class TestServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8083));
        ssc.configureBlocking(false);
        System.out.println("server started, listening on :" + ssc.getLocalAddress());
        // Select 轮询监听channel事件（这里是注册连接事件）将其注册到new出来的`ServerSocketChannel`
        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    ssc.register(selector, SelectionKey.OP_ACCEPT);
                    System.out.println(Thread.currentThread().getName() + "注册完毕");
                }
            }).start();
        }
    }

}
