package com.netty.demo_13_8.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class NettyClient {


    public static void main(String[] args) {
        new NettyClient().connect("localhost", 8083);
    }

    public void connect(String ipHost, int port) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.AUTO_READ, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        // 基于换行符号
                        pipeline.addLast(new LineBasedFrameDecoder(1024));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        pipeline.addLast(new StringDecoder(Charset.forName("GBK")));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        pipeline.addLast(new StringEncoder(Charset.forName("GBK")));
                        // 在管道中添加我们自己的接收数据实现方法
                        pipeline.addLast(new NettyClientHandler());
                    }
                });
        try {
            ChannelFuture f = b.connect(ipHost, port).sync();
            f.addListener(new NettyClientListener());
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGroup.shutdownGracefully();
        }
    }
}
