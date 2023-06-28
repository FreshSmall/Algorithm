package com.netty.demo_13_11.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.nio.charset.Charset;

public class Server {
    public static void main(String[] args) {
        new Server().bing(8090);
    }

    private void bing(int port) {
        //配置服务端NIO线程组
        EventLoopGroup parentGroup = new NioEventLoopGroup(); //NioEventLoopGroup extends MultithreadEventLoopGroup Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors() * 2));
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)    //非阻塞模式
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 基于换行符号
                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            // 流量分块
                            channel.pipeline().addLast(new ChunkedWriteHandler());
                            channel.pipeline().addLast(new MyServerChunkHandler());
//                            // 解码转String，注意调整自己的编码格式GBK、UTF-8
                            channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                            // 解码转String，注意调整自己的编码格式GBK、UTF-8
                            channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                            // 在管道中添加我们自己的接收数据实现方法
                            channel.pipeline().addLast(new MyServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            System.out.println("itstack-demo-netty server start done. {关注公众号：bugstack虫洞栈，获取源码}");
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            childGroup.shutdownGracefully();
            parentGroup.shutdownGracefully();
        }

    }
}
