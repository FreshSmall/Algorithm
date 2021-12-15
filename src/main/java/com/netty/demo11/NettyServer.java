/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yinchao
 * @description 实现 udp 协议发送消息
 * @team wuhan operational dev.
 * @date 2021/12/14 17:17
 **/
public class NettyServer {

    public void bind(int port) {
        //配置服务端的NIO线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(boosGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BROADCAST, true)
            .option(ChannelOption.SO_RCVBUF, 2048 * 1024)
            .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new MyServerHandler());
                }
            });

        try {
            //绑定端口等待同步成功
            ChannelFuture f = b.bind(port).sync();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.......");
            //等待服务监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程资源
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8083;
        new NettyServer().bind(port);
    }


}
