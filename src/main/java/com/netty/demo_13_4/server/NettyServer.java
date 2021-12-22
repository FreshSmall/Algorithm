/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.server;

import com.netty.demo_13_4.codec.ObjDecoder;
import com.netty.demo_13_4.codec.ObjEncoder;
import com.netty.demo_13_4.dto.FileTransferProtocol;
import com.netty.demo_13_4.dto.MessageInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/21 11:42
 **/
public class NettyServer {

    //配置服务端NIO线程组
    private EventLoopGroup parentGroup = new NioEventLoopGroup(); //NioEventLoopGroup extends MultithreadEventLoopGroup Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
    private EventLoopGroup childGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture bind(int port) {
        ServerBootstrap b = new ServerBootstrap();
        ChannelFuture f = null;
        b.group(parentGroup, childGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 128)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    ChannelPipeline pipeline = channel.pipeline();
                    // 解码转String，注意调整自己的编码格式GBK、UTF-8
                    pipeline.addLast(new ObjDecoder(FileTransferProtocol.class));
                    pipeline.addLast(new ObjEncoder(FileTransferProtocol.class));
                    pipeline.addLast(new NettyServerHandler());
                }
            });
        try {
            //绑定端口等待同步成功
            f = b.bind(port).syncUninterruptibly();
            this.channel = f.channel();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.......");
            //等待服务监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程资源
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
        return f;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }
}
