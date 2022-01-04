package com.netty.demo_13_8.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bind(8083);
    }

    public void bind(int port) {
        EventLoopGroup parentLoop = new NioEventLoopGroup();
        EventLoopGroup childLoop = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parentLoop, childLoop)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        /**
                         * 心跳监测
                         * 1、readerIdleTimeSeconds 读超时时间
                         * 2、writerIdleTimeSeconds 写超时时间
                         * 3、allIdleTimeSeconds    读写超时时间
                         * 4、TimeUnit.SECONDS 秒[默认为秒，可以指定]
                         */
                        pipeline.addLast(new IdleStateHandler(2, 2, 2));
                        // 基于换行符号
                        pipeline.addLast(new LineBasedFrameDecoder(1024));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        pipeline.addLast(new StringDecoder(Charset.forName("GBK")));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        pipeline.addLast(new StringEncoder(Charset.forName("GBK")));
                        pipeline.addLast(new NettyServerHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.......");
            //等待服务监听端口关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            parentLoop.shutdownGracefully();
            childLoop.shutdownGracefully();
        }
    }
}
