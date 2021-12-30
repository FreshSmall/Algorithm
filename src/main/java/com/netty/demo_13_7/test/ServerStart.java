package com.netty.demo_13_7.test;

import com.netty.demo_13_7.server.NettyServer;

public class ServerStart {

    public static void main(String[] args) {
        NettyServer server = new NettyServer();
        new Thread(server).start();
        System.out.println("netty server 已经启动");
    }
}
