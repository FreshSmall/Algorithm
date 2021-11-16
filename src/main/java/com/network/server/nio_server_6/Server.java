/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/16 14:47
 **/
public class Server {

    // 默认的Reader为2
    private static final int DEFAULT_READER_SIZE = 2;
    // 默认的Handler为4
    private static final int DEFAULT_HANDLER_SIZE = 4;

    private final BlockingQueue<Call> callQueue = new LinkedBlockingQueue<>();
    private final Handler[] handlers;
    private final int port;

    private Acceptor accepter;

    public Server(int port) {
        this.port = port;
        this.handlers = new Handler[DEFAULT_HANDLER_SIZE];
    }

    public void start() {
        System.out.println("start Base64 Server...");
        // 先启动Accepter
        this.accepter = new Acceptor(this.port, callQueue);
        new Thread(accepter).start();

        // 再启动Handler
        for (int i = 0; i < this.handlers.length; i++) {
            this.handlers[i] = new Handler(callQueue);
            new Thread(this.handlers[i]).start();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8083);
        server.start();
    }

}
