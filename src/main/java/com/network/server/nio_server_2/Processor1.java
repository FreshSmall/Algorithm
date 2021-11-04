/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/4 11:04
 **/
@Slf4j
public class Processor1 implements Callable {

    private SelectionKey key;

    public Processor1(SelectionKey key) {
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
            ByteBuffer bufferToWrite = ByteBuffer.wrap("I am Server\n".getBytes());
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
