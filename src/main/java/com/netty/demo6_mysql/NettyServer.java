/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo6_mysql;

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
 * @description 实现NettyServer 群发消息
 * @team wuhan operational dev.
 * @date 2021/12/14 17:17
 **/
public class NettyServer {

    public void bind(int port) {
        //配置服务端的NIO线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                System.out.println(Thread.currentThread().getName() + ",服务器初始化通道.....");
                //socketChannel.pipeline().addLast(new MySqlPacketDecoder());
                // 解码
                //socketChannel.pipeline().addLast(new StringDecoder());
                // 编码
                //socketChannel.pipeline().addLast(new StringEncoder());
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
        int port = 8090;
        new NettyServer().bind(port);
    }


}
