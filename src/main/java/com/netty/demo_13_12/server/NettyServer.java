package com.netty.demo_13_12.server;

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
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
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
                        // 基于换行符号
                        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                        // 流量整形；writeLimit/readLimit{0 or a limit in bytes/s}
                        channel.pipeline().addLast(new GlobalTrafficShapingHandler(channel.eventLoop().parent(), 10, 10));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                        // 在管道中添加我们自己的接收数据实现方法
                        channel.pipeline().addLast(new MyServerHandler());
                        channel.closeFuture().addListener(future -> {
                            log.info("channel close future: {}", future);
                        });
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
